package foodbank.it.web.dto;

public class OrgClientReportDto {
	
	private String societe;	
	   
	private Integer  nPers;
	    
	private Integer  nFam;
	   
	private Integer  nNour;
	    
	private Integer  nBebe;
	   
	private Integer  nEnf;
	   
	private Integer  nAdo;
	    
	private Integer  n1824;	   
	   	    
	private Integer  nSen;

	public OrgClientReportDto(String societe, Integer nPers, Integer nFam, Integer nNour, Integer nBebe, Integer nEnf,
			Integer nAdo, Integer n1824, Integer nSen) {
		super();
		this.societe = societe;
		this.nPers = nPers;
		this.nFam = nFam;
		this.nNour = nNour;
		this.nBebe = nBebe;
		this.nEnf = nEnf;
		this.nAdo = nAdo;
		this.n1824 = n1824;
		this.nSen = nSen;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public Integer getnPers() {
		return nPers;
	}

	public void setnPers(Integer nPers) {
		this.nPers = nPers;
	}

	public Integer getnFam() {
		return nFam;
	}

	public void setnFam(Integer nFam) {
		this.nFam = nFam;
	}

	public Integer getnNour() {
		return nNour;
	}

	public void setnNour(Integer nNour) {
		this.nNour = nNour;
	}

	public Integer getnBebe() {
		return nBebe;
	}

	public void setnBebe(Integer nBebe) {
		this.nBebe = nBebe;
	}

	public Integer getnEnf() {
		return nEnf;
	}

	public void setnEnf(Integer nEnf) {
		this.nEnf = nEnf;
	}

	public Integer getnAdo() {
		return nAdo;
	}

	public void setnAdo(Integer nAdo) {
		this.nAdo = nAdo;
	}

	public Integer getN1824() {
		return n1824;
	}

	public void setN1824(Integer n1824) {
		this.n1824 = n1824;
	}

	public Integer getnSen() {
		return nSen;
	}

	public void setnSen(Integer nSen) {
		this.nSen = nSen;
	}
	
	
	
	
}
