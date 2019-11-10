package elec5619.psychologyservice.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import elec5619.psychologyservice.domain.MBITQuestion;
import elec5619.psychologyservice.domain.MBTIResponse;
import elec5619.psychologyservice.domain.PersonalityDimentionType;
import elec5619.psychologyservice.domain.PersonalityType;
import elec5619.psychologyservice.domain.PersonalityTypes;
import elec5619.psychologyservice.domain.User;
import elec5619.psychologyservice.dto.UserDTO;

@Service
public class PsychologyService {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${user.rabbitmq.exchange}")
	private String userExchange;
	
	@Value("${user.rabbitmq.routingkey.match.update}")
	private String updateKey;
	
	

	private List<MBITQuestion> mbitQuestions = new ArrayList<MBITQuestion>();

	private ModelMapper mapper = new ModelMapper();

	private static Logger log = LoggerFactory.getLogger(PsychologyService.class);

	private int[][] compatibilityMatrix = new int[16][16];

	public PsychologyService() {

		loadMBTIQuestions();
		loadCompatibilityMatrix();

	}

	public List<MBITQuestion> getMBITQuestionsList() {
		return this.mbitQuestions;
	}

	/**
	 * @author ahmed
	 * 
	 *         Desctipton :
	 * 
	 *         // process all answers and post result toclient rservice via rabbit
	 *         MQ - for calculation algorithm please refer to
	 *         http://www.thepersonalitypage.com ..
	 * @param user
	 * @param answers
	 */

	public void processAnswers(List<MBTIResponse> responseList, User user) throws Exception {
		// ensure answers are sorted
		Collections.sort(responseList);

		// this matrix is actual representation of answer sheet & need to be like this
		// for further processing
		char[][] answer = new char[10][7];

		// current pointer
		int x = 0;
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 7; c++) {
				answer[r][c] = responseList.get(x++).getAnswer();
			}
		}

		// Compress column calclutaons
		// A default value of 0 for arrays of integral types is guaranteed by the
		// language spec:
		// we have 7x2 matrix of all coumns representing a and b score separate
		int[][] cscore = new int[7][2];
		for (int i = 0; i < 7; i++) {

			for (int j = 0; j < 10; j++) {
				if (answer[j][i] == 'A' || answer[j][i] == 'a') {
					cscore[i][0]++;
				} else {
					cscore[i][1]++;
				}
			}

			// now we computer C1, C23, C45 & C67 & present in following array note C1 being
			// 1x2 array and so on others
			int[][] C = new int[4][2];

			C[0] = cscore[0];
			x = 1;
			for (i = 1; i < 4; i++) { // C2+C3=C23
				C[i][0] = cscore[x][0] + cscore[x + 1][0];
				C[i][1] = cscore[x][1] + cscore[x + 1][1];
				x += 2;
			}

			PersonalityType personalityType = computePersonalityType(C);

			user.setPersonalityTypeKey(personalityType.getKey());

			rabbitTemplate.convertAndSend(userExchange, updateKey, mapper.map(user, UserDTO.class));

		}

	}

	public int getMatchScore(String key1, String key2) {
		int k1 = decodeKey(key1);
		int k2 = decodeKey(key2);
		return compatibilityMatrix[k1][k2] * 100;
	}

	private int decodeKey(String key) {
		return PersonalityTypes.valueOf(key).ordinal();
	}

	private PersonalityType computePersonalityType(int[][] C) throws Exception {

		// Builder pattern
		PersonalityType.Builder personalityTypeBuilder = PersonalityType.Builder.getInstance();

		// first dimention
		if (C[0][0] > C[0][1]) {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.E);
		} else {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.I);
		}
		// seconf dimention
		if (C[1][0] > C[1][1]) {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.S);
		} else {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.N);
		}

		// first dimention
		if (C[2][0] > C[2][1]) {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.T);
		} else {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.F);
		}

		// first dimention
		if (C[3][0] > C[3][1]) {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.J);
		} else {
			personalityTypeBuilder.setDimention(PersonalityDimentionType.P);
		}

		return personalityTypeBuilder.build();

	}

	private void loadMBTIQuestions() {

		try (BufferedReader br = new BufferedReader(
				new FileReader(getClass().getClassLoader().getResource("data/MBTI.txt").getFile()))) {

			// strictly formatted file to read each line new question the two line option
			for (String line; (line = br.readLine()) != null;) {
				MBITQuestion question = new MBITQuestion();
				// start of line question number then dot then questiontext of question
				int pointer = line.indexOf('.');
				question.setNum(Integer.parseInt(line.substring(0, pointer).trim()));
				question.setQuestiontext(line.substring(pointer + 1).trim());
				line = br.readLine();
				question.setOptionA(line.substring(2));// ignore letter a.
				line = br.readLine();
				question.setOptionB(line.substring(2));// ignore letter b.
				mbitQuestions.add(question);
			}
			log.info("MBIT questions loaded for presentation");
			// line is not visible here.
		} catch (IOException e) {
			log.error("Error in loading MBIT question service is corrupt -Fatal Error ");

		}

	}

	private void loadCompatibilityMatrix() {

		try (BufferedReader br = new BufferedReader(
				new FileReader(getClass().getClassLoader().getResource("data/compatibilitymatrix.txt").getFile()))) {

			// strictly formatted and coded matrix file to read
			// 16 x 16 matrix
			for (int i = 0; i < 16; i++) {
				char[] entry = br.readLine().toCharArray();
				if (entry.length != 16)
					log.error("Error in loading MBIT question service is corrupt -Fatal Error ");
				for (int j = 0; j < 16; j++) {
					compatibilityMatrix[i][j] = entry[j];
				}
			}

			log.info("Compatibility Matrix loaded ");

		} catch (IOException e) {
			log.error("Error in loading Matrix service is corrupt -Fatal Error ");
		}

	}

}