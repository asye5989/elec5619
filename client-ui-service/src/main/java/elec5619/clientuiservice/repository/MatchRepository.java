package elec5619.clientuiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import elec5619.clientuiservice.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

	@Query("delete from match m where m.fk_male_partner_id:id ")
	public void deleteMalePartnerMatch(@Param(value = "id") Long id);
	
	@Query("delete from match m where m.fk_female_partner_id:id ")
	public void deleteFemalePartnerMatch(@Param(value = "id") Long id);
	
	@Query(" from match m where m.fk_male_partner_id:id ")
	public Match getMalePartnerMatch(@Param(value = "id") Long id);
	
	@Query(" from match m where m.fk_female_partner_id:id ")
	public Match getFemalePartnerMatch(@Param(value = "id") Long id);

}
