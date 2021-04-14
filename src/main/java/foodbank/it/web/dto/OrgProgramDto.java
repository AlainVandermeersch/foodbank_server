package foodbank.it.web.dto;

public class OrgProgramDto {
	private int lienDis;
	  private int lienBanque;
	  private int lienDepot;
	    
	    private int luam;
	    
	    private int lupm;
	    
	    private int tuam;
	    
	    private int tupm;
	    
	    private int weam;
	    
	    private int wepm;
	    
	    private int tham;
	    
	    private int thpm;
	    
	    private int fram;
	    
	    private int frpm;
	    
	    private int saam;
	    
	    private int sapm;
	    
	    private int sunam;
	    
	    private int sunpm;
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
	    
	    private int porc;
	    private int legFrais;
	    
	    private int congel;
	    private String congelCap;
	    
	    private int auditor;
	    private String dateAudit;
	    private long lastAudit;
		public OrgProgramDto(int lienDis, int lienBanque, int lienDepot, int luam, int lupm, int tuam, int tupm,
				int weam, int wepm, int tham, int thpm, int fram, int frpm, int saam, int sapm, int sunam, int sunpm,
				String reluam, String relupm, String retuam, String retupm, String reweam, String rewepm, String retham,
				String rethpm, String refram, String refrpm, String resaam, String resapm, String resunam,
				String resunpm, int porc, int legFrais, int congel, String congelCap, int auditor, String dateAudit,
				long lastAudit) {
			super();
			this.lienDis = lienDis;
			this.lienBanque = lienBanque;
			this.lienDepot = lienDepot;
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
			this.porc = porc;
			this.legFrais = legFrais;
			this.congel = congel;
			this.congelCap = congelCap;
			this.auditor = auditor;
			this.dateAudit = dateAudit;
			this.lastAudit = lastAudit;
		}
		public int getLienDis() {
			return lienDis;
		}
		public void setLienDis(int lienDis) {
			this.lienDis = lienDis;
		}
		public int getLienBanque() {
			return lienBanque;
		}
		public void setLienBanque(int lienBanque) {
			this.lienBanque = lienBanque;
		}
		public int getLienDepot() {
			return lienDepot;
		}
		public void setLienDepot(int lienDepot) {
			this.lienDepot = lienDepot;
		}
		public int getLuam() {
			return luam;
		}
		public void setLuam(int luam) {
			this.luam = luam;
		}
		public int getLupm() {
			return lupm;
		}
		public void setLupm(int lupm) {
			this.lupm = lupm;
		}
		public int getTuam() {
			return tuam;
		}
		public void setTuam(int tuam) {
			this.tuam = tuam;
		}
		public int getTupm() {
			return tupm;
		}
		public void setTupm(int tupm) {
			this.tupm = tupm;
		}
		public int getWeam() {
			return weam;
		}
		public void setWeam(int weam) {
			this.weam = weam;
		}
		public int getWepm() {
			return wepm;
		}
		public void setWepm(int wepm) {
			this.wepm = wepm;
		}
		public int getTham() {
			return tham;
		}
		public void setTham(int tham) {
			this.tham = tham;
		}
		public int getThpm() {
			return thpm;
		}
		public void setThpm(int thpm) {
			this.thpm = thpm;
		}
		public int getFram() {
			return fram;
		}
		public void setFram(int fram) {
			this.fram = fram;
		}
		public int getFrpm() {
			return frpm;
		}
		public void setFrpm(int frpm) {
			this.frpm = frpm;
		}
		public int getSaam() {
			return saam;
		}
		public void setSaam(int saam) {
			this.saam = saam;
		}
		public int getSapm() {
			return sapm;
		}
		public void setSapm(int sapm) {
			this.sapm = sapm;
		}
		public int getSunam() {
			return sunam;
		}
		public void setSunam(int sunam) {
			this.sunam = sunam;
		}
		public int getSunpm() {
			return sunpm;
		}
		public void setSunpm(int sunpm) {
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
		public int getPorc() {
			return porc;
		}
		public void setPorc(int porc) {
			this.porc = porc;
		}
		public int getLegFrais() {
			return legFrais;
		}
		public void setLegFrais(int legFrais) {
			this.legFrais = legFrais;
		}
		public int getCongel() {
			return congel;
		}
		public void setCongel(int congel) {
			this.congel = congel;
		}
		public String getCongelCap() {
			return congelCap;
		}
		public void setCongelCap(String congelCap) {
			this.congelCap = congelCap;
		}
		public int getAuditor() {
			return auditor;
		}
		public void setAuditor(int auditor) {
			this.auditor = auditor;
		}
		public String getDateAudit() {
			return dateAudit;
		}
		public void setDateAudit(String dateAudit) {
			this.dateAudit = dateAudit;
		}
		public long getLastAudit() {
			return lastAudit;
		}
		public void setLastAudit(long lastAudit) {
			this.lastAudit = lastAudit;
		}
	    
}

