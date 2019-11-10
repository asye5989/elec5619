package elec5619.clientuiservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user_match")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne
	@JoinColumn(name = "fk_female_partner_id",unique = true)
	private User femalePartner;
	
	
	@OneToOne
	@JoinColumn(name = "fk_male_partner_id",unique = true) // only monogamous relationships 
	private User malePartner;

	
	@Column(name="score")
	private int score=-1;
	
	@Column(name="score_date")
	private String scoreDate; 

}
