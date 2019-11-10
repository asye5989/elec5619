package elec5619.clientuiservice.dto;

import java.io.Serializable;

import elec5619.clientuiservice.domain.User;
import elec5619.common.domain.Gender;
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

public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long id;
	protected String username;
	protected String fullname;
	protected Gender gender;
	protected String personalityTypeKey;
	protected User partner;
	protected Long matchId;
}
