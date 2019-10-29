package elect5619.gatewayservice.dto;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ResponseBody
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Deprecated
public class ClientDashboardDTO {
	UserDTO user;
	UserDTO partner;
	String result;
}
