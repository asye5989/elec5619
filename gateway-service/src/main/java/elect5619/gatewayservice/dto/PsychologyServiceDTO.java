package elect5619.gatewayservice.dto;

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
@Deprecated
public class PsychologyServiceDTO {
	UserDTO partner;
	String result;
}
