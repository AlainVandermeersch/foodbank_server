package foodbank.it.service;

public class SearchAuditCriteria {
	
	private String user;
	private Integer lienBanque;         
    private Integer idDis;    
    private String societe;
	public SearchAuditCriteria(String user, Integer lienBanque, Integer idDis, String societe) {
		super();
		this.user = user;
		this.lienBanque = lienBanque;
		this.idDis = idDis;
		this.societe = societe;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	public Integer getIdDis() {
		return idDis;
	}
	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	
  
   

}
