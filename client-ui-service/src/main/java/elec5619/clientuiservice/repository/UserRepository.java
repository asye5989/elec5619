package elec5619.clientuiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elec5619.clientuiservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	

}
