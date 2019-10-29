package elec5619.clientuiservice.dto;

import elec5619.clientuiservice.domain.User;
import elec5619.common.domain.BaseUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserDTO extends BaseUser {
	protected User partner;
	protected Long matchId;
}
