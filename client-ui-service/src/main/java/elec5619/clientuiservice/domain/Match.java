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

@Entity
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
	@JoinColumn(name = "fk_male_partner_id",unique = true) // only monogamous relationships 
	private User malePartner;

	@OneToOne
	@JoinColumn(name = "fk_female_partner_id",unique = true)
	private User femalePartner;
	
	@Column
	private int matchScore=-1;

}
