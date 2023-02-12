package foodbank.it.web.dto;

public class BanqueClientReportDto {
	private String bankShortName;
	private short isDepot;
	private short nonAgreed;
	private short gestBen;
	private Long orgCount;

	private Long nFam;

	private Long nPers;

	private Long nNour;

	private Long nBebe;

	private Long nEnf;

	private Long nAdo;

	private Long n1824;
	
	private Long nSen;

	public BanqueClientReportDto(String bankShortName, short isDepot, short nonAgreed, short gestBen,Long orgCount,
								 Long nFam,
								 Long nPers,
								 Long nNour,
								 Long nBebe,
								 Long nEnf,
								 Long nAdo,
								 Long n1824,
								 Long nSen) {
		super();
		this.bankShortName = bankShortName;
		this.isDepot = isDepot;
		this.nonAgreed = nonAgreed;
		this.gestBen = gestBen;
		this.orgCount = orgCount;
		this.nFam = nFam;
		this.nPers = nPers;
		this.nNour = nNour;
		this.nBebe= nBebe;
		this.nEnf = nEnf;
		this.nAdo = nAdo;
		this.n1824 = n1824;
		this.nSen = nSen;
		
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	public short getIsDepot() {
		return isDepot;
	}

	public void setIsDepot(short isDepot) {
		this.isDepot = isDepot;
	}

	public short getNonAgreed() {
		return nonAgreed;
	}

	public void setNonAgreed(short nonAgreed) {
		this.nonAgreed = nonAgreed;
	}

	public short getGestBen() {
		return gestBen;
	}

	public void setGestBen(short gestBen) {
		this.gestBen = gestBen;
	}

	public Long getOrgCount() {
		return orgCount;
	}

	public void setOrgCount(Long orgCount) {
		this.orgCount = orgCount;
	}

	public Long getnFam() {
		return nFam;
	}

	public void setnFam(Long nFam) {
		this.nFam = nFam;
	}

	public Long getnPers() {
		return nPers;
	}

	public void setnPers(Long nPers) {
		this.nPers = nPers;
	}

	public Long getnNour() {
		return nNour;
	}

	public void setnNour(Long nNour) {
		this.nNour = nNour;
	}

	public Long getnBebe() {
		return nBebe;
	}

	public void setnBebe(Long nBebe) {
		this.nBebe = nBebe;
	}

	public Long getnEnf() {
		return nEnf;
	}

	public void setnEnf(Long nEnf) {
		this.nEnf = nEnf;
	}

	public Long getnAdo() {
		return nAdo;
	}

	public void setnAdo(Long nAdo) {
		this.nAdo = nAdo;
	}

	public Long getN1824() {
		return n1824;
	}

	public void setN1824(Long n1824) {
		this.n1824 = n1824;
	}

	public Long getnSen() {
		return nSen;
	}

	public void setnSen(Long nSen) {
		this.nSen = nSen;
	}

}
