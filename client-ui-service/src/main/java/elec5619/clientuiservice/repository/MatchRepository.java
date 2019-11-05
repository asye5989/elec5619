package elec5619.clientuiservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import elec5619.clientuiservice.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {


	@Modifying
	@Transactional
	@Query("delete from user_match m where m.malePartner.id= :id ")
	public void deleteMalePartnerMatch(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("delete from user_match m where m.femalePartner.id= :id ")
	public void deleteFemalePartnerMatch(@Param("id") Long id);
	
	@Query(" from user_match m where m.malePartner.id= :id ")
	public Match getMalePartnerMatch(@Param("id") Long id);
	
	@Query(" from user_match m where  m.femalePartner.id= :id ")
	public Match getFemalePartnerMatch(@Param("id") Long id);
	
	@Query(" from user_match m where  m.score< 0 ")
	public List<Match> findUnScoredMatches();
	
}
