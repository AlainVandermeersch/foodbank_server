package foodbank.it.service;

public class SearchMailListCriteria {
	private Integer lienBanque;
	private Integer lienDis;
	private String rights;
	public SearchMailListCriteria(Integer lienBanque, Integer lienDis,String rights) {
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;	 
		this.rights = rights;
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
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	

}
