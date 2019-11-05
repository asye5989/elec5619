package elec5619.psychologyservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class MatchDTO implements Serializable {
	private static final long serialVersionUID = -6856132863861902289L;
	private String type1, type2;
	private Long matchId;
	private int score;

}
