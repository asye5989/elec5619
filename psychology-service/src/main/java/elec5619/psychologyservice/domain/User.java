package elec5619.psychologyservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**This class not inherit form BaseUser as it only using one field
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
	protected Long UserId; 
	protected String personalityTypeKey;
}
 