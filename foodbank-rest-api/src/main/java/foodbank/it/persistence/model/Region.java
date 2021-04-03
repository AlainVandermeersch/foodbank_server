package foodbank.it.persistence.model;
//Generated with g9.

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
 
 @ManyToOne
 @JoinColumn(name="Bank_link")
 private Banque banqueObject;

 /** Default constructor. */
 public Region() {
     super();
 }

 public Region(int regId, String regName, Banque banqueObject) {
	super();
	this.regId = regId;
	this.regName = regName;
	this.banqueObject = banqueObject;
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

public Banque getBanqueObject() {
	return banqueObject;
}

public void setBanqueObject(Banque banqueObject) {
	this.banqueObject = banqueObject;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((banqueObject == null) ? 0 : banqueObject.hashCode());
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
	if (banqueObject == null) {
		if (other.banqueObject != null)
			return false;
	} else if (!banqueObject.equals(other.banqueObject))
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

@Override
public String toString() {
	return "Region [regId=" + regId + ", regName=" + regName + ", banqueObject=" + banqueObject + "]";
}



}
