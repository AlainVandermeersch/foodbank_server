package foodbank.it.service;

public class SearchMailListCriteria {
	private Integer lienBanque;
	private Integer lienDis;
	private Integer regId;
	public SearchMailListCriteria(Integer lienBanque, Integer lienDis,Integer regId) {
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;	 
		this.regId = regId;
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
	
	

}
