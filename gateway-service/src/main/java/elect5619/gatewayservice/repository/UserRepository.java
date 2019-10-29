/**
 * 
 */
package elect5619.gatewayservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import elect5619.gatewayservice.domain.User;

/**
 * @author ahmed
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User>  findFirstByUsernameAndPassword(String email, String sha512);

	public Optional<User> findFirstByAssignedToken(String token);

	@Query("from user u where u.username=:username")
	public Optional<User> findFirstByUsername(@Param("username") String username);

}
