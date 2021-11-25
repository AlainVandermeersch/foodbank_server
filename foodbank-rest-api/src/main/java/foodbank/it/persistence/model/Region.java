package foodbank.it.persistence.model;
//Generated with g9.

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

@Entity(name="regions")
public class Region implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = -1928137370950121810L;

/** Primary key. */
 protected static final String PK = "regId";
 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY) 
 @Column(name="Reg_ID", unique=true, nullable=false, precision=10)
 private int regId;
 @Column(name="Reg_name", nullable=false, length=25)
 private String regName;
 @Column(name="Bank_link", nullable=false, precision=3)
 private short bankLink;
 
 @Formula("(select d.bank_short_name from banques d where d.bank_id = Bank_link)")
 private String bankShortName;

 /** Default constructor. */
 public Region() {
     super();
 }

 public Region(int regId, String regName, short bankLink) {
	super();
	this.regId = regId;
	this.regName = regName;
	this.bankLink = bankLink;
}

/**
  * Access method for regId.
  *
  * @return the current value of regId
  */
 public int getRegId() {
     return regId;
 }

 /**
  * Setter method for regId.
  *
  * @param aRegId the new value for regId
  */
 public void setRegId(int aRegId) {
     regId = aRegId;
 }

 /**
  * Access method for regName.
  *
  * @return the current value of regName
  */
 public String getRegName() {
     return regName;
 }

 /**
  * Setter method for regName.
  *
  * @param aRegName the new value for regName
  */
 public void setRegName(String aRegName) {
     regName = aRegName;
 }

public short getBankLink() {
	return bankLink;
}

public void setBankLink(short bankLink) {
	this.bankLink = bankLink;
}

public String getBankShortName() {
	return bankShortName;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + bankLink;
	result = prime * result + regId;
	result = prime * result + ((regName == null) ? 0 : regName.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Region other = (Region) obj;
	if (bankLink != other.bankLink)
		return false;
	if (regId != other.regId)
		return false;
	if (regName == null) {
		if (other.regName != null)
			return false;
	} else if (!regName.equals(other.regName))
		return false;
	return true;
}






}
