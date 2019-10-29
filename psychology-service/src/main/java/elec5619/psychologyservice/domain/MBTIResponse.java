package elec5619.psychologyservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MBTIResponse implements Comparable<MBTIResponse> {
 
	protected int  questionNumber;
	protected char answer;

	@Override
	public int compareTo(MBTIResponse o) {
		if (this.questionNumber > o.questionNumber) {
			return 1;
		} else {
			if (this.questionNumber < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
