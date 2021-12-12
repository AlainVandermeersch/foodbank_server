// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

@Entity(name="audit")
public class Audit implements Serializable {

  
    /**
	 * 
	 */
	private static final long serialVersionUID = 998718967030069510L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="audit_id", unique=true, nullable=false, precision=10)
    private int auditId;
    @Column(nullable=false, length=50)
    private String user;
    @Column(name="date_in", nullable=false)
    private LocalDateTime dateIn;
    @Column(name="ip_address", length=20)
    private String ipAddress;
    @Column(name="id_dis", nullable=false, precision=10)
    private int idDis;
    @Formula("(select d.societe from organisations d where d.id_dis = id_dis)")
    private String societe;
    @Formula("(select d.lien_banque from organisations d where d.id_dis = id_dis)")
    private Integer lienBanque; 

    /** Default constructor. */
    public Audit() {
        super();
    }

    public Audit(int auditId, String user, String ipAddress, int idDis) {
		super();
		this.auditId = auditId;
		this.user = user;
		this.ipAddress = ipAddress;
		this.idDis = idDis;
	}

	/**
     * Access method for auditId.
     *
     * @return the current value of auditId
     */
    public int getAuditId() {
        return auditId;
    }

    /**
     * Setter method for auditId.
     *
     * @param aAuditId the new value for auditId
     */
    public void setAuditId(int aAuditId) {
        auditId = aAuditId;
    }

    /**
     * Access method for user.
     *
     * @return the current value of user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter method for user.
     *
     * @param aUser the new value for user
     */
    public void setUser(String aUser) {
        user = aUser;
    }

    /**
     * Access method for dateIn.
     *
     * @return the current value of dateIn
     */
    public LocalDateTime getDateIn() {
        return dateIn;
    }

    /**
     * Setter method for dateIn.
     *
     * @param aDateIn the new value for dateIn
     */
    public void setDateIn(LocalDateTime aDateIn) {
        dateIn = aDateIn;
    }

    /**
     * Access method for ipAddress.
     *
     * @return the current value of ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Setter method for ipAddress.
     *
     * @param aIpAddress the new value for ipAddress
     */
    public void setIpAddress(String aIpAddress) {
        ipAddress = aIpAddress;
    }

	public int getIdDis() {
		return idDis;
	}

	public String getSociete() {
		return societe;
	}

	public Integer getLienBanque() {
		return lienBanque;
	}

}
