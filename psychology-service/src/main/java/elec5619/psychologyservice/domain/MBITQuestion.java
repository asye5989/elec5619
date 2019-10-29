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
	protected  Integer number;
	protected String text;
	protected String optionA;
	protected String optionB;
}
