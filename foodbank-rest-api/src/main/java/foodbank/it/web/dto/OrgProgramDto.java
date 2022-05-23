package foodbank.it.web.dto;

public class OrgProgramDto {
	private int lienDis;
	  private int lienBanque;
	  private int lienDepot;
	    
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
	    
	    private boolean porc;
	    private boolean legFrais;
	    
	    private boolean congel;
	    private String congelCap;
	    
	    private Integer auditor;
	    private String dateAudit;
	    private Long lastAudit;
		public OrgProgramDto(int lienDis, int lienBanque, int lienDepot, boolean luam, boolean lupm, boolean tuam, boolean tupm,
				boolean weam, boolean wepm, boolean tham, boolean thpm, boolean fram, boolean frpm, boolean saam, boolean sapm, boolean sunam, boolean sunpm,
				String reluam, String relupm, String retuam, String retupm, String reweam, String rewepm, String retham,
				String rethpm, String refram, String refrpm, String resaam, String resapm, String resunam,
				String resunpm, boolean porc, boolean legFrais, boolean congel, String congelCap, Integer auditor, String dateAudit,
				Long lastAudit) {
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
		public boolean getLuam() {
			return luam;
		}
		public void setLuam(boolean luam) {
			this.luam = luam;
		}
		public boolean getLupm() {
			return lupm;
		}
		public void setLupm(boolean lupm) {
			this.lupm = lupm;
		}
		public boolean getTuam() {
			return tuam;
		}
		public void setTuam(boolean tuam) {
			this.tuam = tuam;
		}
		public boolean getTupm() {
			return tupm;
		}
		public void setTupm(boolean tupm) {
			this.tupm = tupm;
		}
		public boolean getWeam() {
			return weam;
		}
		public void setWeam(boolean weam) {
			this.weam = weam;
		}
		public boolean getWepm() {
			return wepm;
		}
		public void setWepm(boolean wepm) {
			this.wepm = wepm;
		}
		public boolean getTham() {
			return tham;
		}
		public void setTham(boolean tham) {
			this.tham = tham;
		}
		public boolean getThpm() {
			return thpm;
		}
		public void setThpm(boolean thpm) {
			this.thpm = thpm;
		}
		public boolean getFram() {
			return fram;
		}
		public void setFram(boolean fram) {
			this.fram = fram;
		}
		public boolean getFrpm() {
			return frpm;
		}
		public void setFrpm(boolean frpm) {
			this.frpm = frpm;
		}
		public boolean getSaam() {
			return saam;
		}
		public void setSaam(boolean saam) {
			this.saam = saam;
		}
		public boolean getSapm() {
			return sapm;
		}
		public void setSapm(boolean sapm) {
			this.sapm = sapm;
		}
		public boolean getSunam() {
			return sunam;
		}
		public void setSunam(boolean sunam) {
			this.sunam = sunam;
		}
		public boolean getSunpm() {
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
		public boolean getPorc() {
			return porc;
		}
		public void setPorc(boolean porc) {
			this.porc = porc;
		}
		public boolean getLegFrais() {
			return legFrais;
		}
		public void setLegFrais(boolean legFrais) {
			this.legFrais = legFrais;
		}
		public boolean getCongel() {
			return congel;
		}
		public void setCongel(boolean congel) {
			this.congel = congel;
		}
		public String getCongelCap() {
			return congelCap;
		}
		public void setCongelCap(String congelCap) {
			this.congelCap = congelCap;
		}
		public Integer getAuditor() {
			return auditor;
		}
		public void setAuditor(Integer auditor) {
			this.auditor = auditor;
		}
		public String getDateAudit() {
			return dateAudit;
		}
		public void setDateAudit(String dateAudit) {
			this.dateAudit = dateAudit;
		}
		public Long getLastAudit() {
			return lastAudit;
		}
		public void setLastAudit(Long lastAudit) {
			this.lastAudit = lastAudit;
		}
	    
}

