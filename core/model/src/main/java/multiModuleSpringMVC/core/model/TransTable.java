package multiModuleSpringMVC.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRANS_TBL")
public class TransTable {
    
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int id;
	
	@Column(name="LOWER_LIMIT")
	private int lowerLimit;
	
	@Column(name="UPPER_LIMIT")
	private int upperLimit;
	
	@Column(name="EQUIVALENCE")
	private char equivalence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

	public char getEquivalence() {
		return equivalence;
	}

	public void setEquivalence(char equivalence) {
		this.equivalence = equivalence;
	}
	
	
}
