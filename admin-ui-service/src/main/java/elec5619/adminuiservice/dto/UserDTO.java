package elec5619.adminuiservice.dto;

import java.io.Serializable;

import elec5619.common.domain.BaseUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends BaseUser implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 7172695427838634929L;
protected Long matchId;
 protected String password;

}
