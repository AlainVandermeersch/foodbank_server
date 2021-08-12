package foodbank.it.service;

public class SearchMembreMailCriteria {
	private Integer lienBanque;
	private Integer lienDis;
	public SearchMembreMailCriteria(Integer lienBanque, Integer lienDis) {
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;	 
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
	

}
