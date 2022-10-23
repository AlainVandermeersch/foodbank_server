package foodbank.it.service;

public class SearchMailListCriteria {
	private Integer lienBanque;
	private Integer lienDis;
	private Integer regId;
	private Boolean agreed;
	private Boolean feadN;
	private Boolean isDepot;
	private Integer langue;
	private String mailGroup;
	public SearchMailListCriteria(Integer lienBanque, Integer lienDis,Integer regId,Boolean agreed,Boolean feadN,
			Boolean isDepot,Integer langue,String mailGroup) {
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;	 
		this.regId = regId;
		this.agreed = agreed;
		this.feadN = feadN;
		this.isDepot = isDepot;
		this.langue = langue;
		this.mailGroup = mailGroup;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	public Integer getLienDis() {
		return lienDis;
	}
	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Boolean getAgreed() {
		return agreed;
	}
	public void setAgreed(Boolean agreed) {
		this.agreed = agreed;
	}
	public Boolean getFeadN() {
		return feadN;
	}
	public void setFeadN(Boolean feadN) {
		this.feadN = feadN;
	}	
	
	public Boolean getIsDepot() {
		return isDepot;
	}
	public void setIsDepot(Boolean isDepot) {
		this.isDepot = isDepot;
	}
	
	public Integer getLangue() {
		return langue;
	}
	public void setLangue(Integer langue) {
		this.langue = langue;
	}
	public String getMailGroup() {
		return mailGroup;
	}
	public void setMailGroup(String mailGroup) {
		this.mailGroup = mailGroup;
	}
	
	
	
	

}
