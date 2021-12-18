package foodbank.it.web.dto;

public class BanqProgDto {
	private int lienBanque;

	 private boolean luam; 
	 private boolean lupm; 
	 private boolean tuam; 
	 private boolean tupm; 
	 private boolean weam; 
	 private boolean wepm; 
	 private boolean tham; 
	 private boolean thpm; 
	 private boolean fram; 
	 private boolean frpm; 
	 private boolean saam; 
	 private boolean sapm; 
	 private boolean sunam; 
	 private boolean sunpm;
	 
	 private String reluam;
	 private String relupm;
	 private String retuam;
	 private String retupm;
	 private String reweam;
	 private String rewepm;
	 private String retham;
	 private String rethpm;
	 private String refram;
	 private String refrpm;
	 private String resaam;
	 private String resapm;
	 private String resunam;
	 private String resunpm;
	 private boolean cotAnnuelle; 
	 private String cotAmount; 
	 private boolean cotSup;
	 private String cotAmountSup;
	 private String bankShortName;
	 
	
	 
	public BanqProgDto(int lienBanque, boolean luam, boolean lupm, boolean tuam, boolean tupm, boolean weam,
			boolean wepm, boolean tham, boolean thpm, boolean fram, boolean frpm, boolean saam, boolean sapm,
			boolean sunam, boolean sunpm, String reluam, String relupm, String retuam, String retupm, String reweam,
			String rewepm, String retham, String rethpm, String refram, String refrpm, String resaam, String resapm,
			String resunam, String resunpm, boolean cotAnnuelle, String cotAmount, boolean cotSup, String cotAmountSup,
			String bankShortName) {
		super();
		this.lienBanque = lienBanque;
		this.luam = luam;
		this.lupm = lupm;
		this.tuam = tuam;
		this.tupm = tupm;
		this.weam = weam;
		this.wepm = wepm;
		this.tham = tham;
		this.thpm = thpm;
		this.fram = fram;
		this.frpm = frpm;
		this.saam = saam;
		this.sapm = sapm;
		this.sunam = sunam;
		this.sunpm = sunpm;
		this.reluam = reluam;
		this.relupm = relupm;
		this.retuam = retuam;
		this.retupm = retupm;
		this.reweam = reweam;
		this.rewepm = rewepm;
		this.retham = retham;
		this.rethpm = rethpm;
		this.refram = refram;
		this.refrpm = refrpm;
		this.resaam = resaam;
		this.resapm = resapm;
		this.resunam = resunam;
		this.resunpm = resunpm;
		this.cotAnnuelle = cotAnnuelle;
		this.cotAmount = cotAmount;
		this.cotSup = cotSup;
		this.cotAmountSup = cotAmountSup;
		this.bankShortName = bankShortName;
	}
	public int getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(int lienBanque) {
		this.lienBanque = lienBanque;
	}
	public boolean isLuam() {
		return luam;
	}
	public void setLuam(boolean luam) {
		this.luam = luam;
	}
	public boolean isLupm() {
		return lupm;
	}
	public void setLupm(boolean lupm) {
		this.lupm = lupm;
	}
	public boolean isTuam() {
		return tuam;
	}
	public void setTuam(boolean tuam) {
		this.tuam = tuam;
	}
	public boolean isTupm() {
		return tupm;
	}
	public void setTupm(boolean tupm) {
		this.tupm = tupm;
	}
	public boolean isWeam() {
		return weam;
	}
	public void setWeam(boolean weam) {
		this.weam = weam;
	}
	public boolean isWepm() {
		return wepm;
	}
	public void setWepm(boolean wepm) {
		this.wepm = wepm;
	}
	public boolean isTham() {
		return tham;
	}
	public void setTham(boolean tham) {
		this.tham = tham;
	}
	public boolean isThpm() {
		return thpm;
	}
	public void setThpm(boolean thpm) {
		this.thpm = thpm;
	}
	public boolean isFram() {
		return fram;
	}
	public void setFram(boolean fram) {
		this.fram = fram;
	}
	public boolean isFrpm() {
		return frpm;
	}
	public void setFrpm(boolean frpm) {
		this.frpm = frpm;
	}
	public boolean isSaam() {
		return saam;
	}
	public void setSaam(boolean saam) {
		this.saam = saam;
	}
	public boolean isSapm() {
		return sapm;
	}
	public void setSapm(boolean sapm) {
		this.sapm = sapm;
	}
	public boolean isSunam() {
		return sunam;
	}
	public void setSunam(boolean sunam) {
		this.sunam = sunam;
	}
	public boolean isSunpm() {
		return sunpm;
	}
	public void setSunpm(boolean sunpm) {
		this.sunpm = sunpm;
	}
	public String getReluam() {
		return reluam;
	}
	public void setReluam(String reluam) {
		this.reluam = reluam;
	}
	public String getRelupm() {
		return relupm;
	}
	public void setRelupm(String relupm) {
		this.relupm = relupm;
	}
	public String getRetuam() {
		return retuam;
	}
	public void setRetuam(String retuam) {
		this.retuam = retuam;
	}
	public String getRetupm() {
		return retupm;
	}
	public void setRetupm(String retupm) {
		this.retupm = retupm;
	}
	public String getReweam() {
		return reweam;
	}
	public void setReweam(String reweam) {
		this.reweam = reweam;
	}
	public String getRewepm() {
		return rewepm;
	}
	public void setRewepm(String rewepm) {
		this.rewepm = rewepm;
	}
	public String getRetham() {
		return retham;
	}
	public void setRetham(String retham) {
		this.retham = retham;
	}
	public String getRethpm() {
		return rethpm;
	}
	public void setRethpm(String rethpm) {
		this.rethpm = rethpm;
	}
	public String getRefram() {
		return refram;
	}
	public void setRefram(String refram) {
		this.refram = refram;
	}
	public String getRefrpm() {
		return refrpm;
	}
	public void setRefrpm(String refrpm) {
		this.refrpm = refrpm;
	}
	public String getResaam() {
		return resaam;
	}
	public void setResaam(String resaam) {
		this.resaam = resaam;
	}
	public String getResapm() {
		return resapm;
	}
	public void setResapm(String resapm) {
		this.resapm = resapm;
	}
	public String getResunam() {
		return resunam;
	}
	public void setResunam(String resunam) {
		this.resunam = resunam;
	}
	public String getResunpm() {
		return resunpm;
	}
	public void setResunpm(String resunpm) {
		this.resunpm = resunpm;
	}
	public boolean getCotAnnuelle() {
		return cotAnnuelle;
	}
	public void setCotAnnuelle(boolean cotAnnuelle) {
		this.cotAnnuelle = cotAnnuelle;
	}
	public String getCotAmount() {
		return cotAmount;
	}
	public void setCotAmount(String cotAmount) {
		this.cotAmount = cotAmount;
	}
	public boolean getCotSup() {
		return cotSup;
	}
	public void setCotSup(boolean cotSup) {
		this.cotSup = cotSup;
	}
	public String getCotAmountSup() {
		return cotAmountSup;
	}
	public void setCotAmountSup(String cotAmountSup) {
		this.cotAmountSup = cotAmountSup;
	}
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	 
	 
}

