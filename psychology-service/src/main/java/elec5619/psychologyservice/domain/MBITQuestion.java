package elec5619.psychologyservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MBITQuestion {
	protected  Integer num;
	protected String questiontext;
	protected String optionA;
	protected String optionB;
}
