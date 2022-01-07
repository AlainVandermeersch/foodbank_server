package foodbank.it.service;

public class SearchMailListCriteria {
	private Integer lienBanque;
	private Integer lienDis;
	private Integer regId;
	private Boolean agreed;
	private Boolean feadN;	
	private String target;
	public SearchMailListCriteria(Integer lienBanque, Integer lienDis,Integer regId,Boolean agreed,Boolean feadN,String target) {
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;	 
		this.regId = regId;
		this.agreed = agreed;
		this.feadN = feadN;
		this.target = target;
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
	
	

}
