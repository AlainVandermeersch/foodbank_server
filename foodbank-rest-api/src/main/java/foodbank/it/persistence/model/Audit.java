// Generated with g9.

package foodbank.it.persistence.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @Column(name="date_in")
    private LocalDateTime dateIn;
    @Column(name="ip_address", length=20)
    private String ipAddress;
    @Column(name="id_dis", nullable=false, precision=10)
    private int idDis;
    private String application;
    @Formula("(select d.societe from organisations d where d.id_dis = id_dis)")
    private String societe;
    @Formula("(select d.ID_COMPANY from t_user d where d.ID_USER = user)")
    private String bankShortName; 
    @Formula("(select d.USER_NAME from t_user d where d.ID_USER = user)")
    private String userName;   
    @Formula("(select d.rights from t_user d where d.ID_USER = user)")
    private String rights; 

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
		this.dateIn = LocalDateTime.now(); 
		this.application = "FBIT";
		
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
    
    public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public int getIdDis() {
		return idDis;
	}

	public void setIdDis(int idDis) {
		this.idDis = idDis;
	}

	public String getSociete() {
		return societe;
	}
	
	public String getBankShortName() {
		return bankShortName;
	}

	public String getUserName() {
		return userName;
	}

	public String getRights() {
		return rights;
	}

	

	

}
