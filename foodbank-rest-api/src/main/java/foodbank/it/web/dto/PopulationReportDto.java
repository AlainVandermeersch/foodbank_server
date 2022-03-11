package foodbank.it.web.dto;

import java.time.LocalDate;

public class PopulationReportDto {
	  private LocalDate dateStat;
	  private Integer lienBanque;
	  private long nFam;
	  
	    private long nPers;
	    
	    private long nNour;
	  
	    private long nBebe;
	    
	    private long nEnf;
	    
	    private long nAdo;
	   
	    private long n1824;
	   
	   
	    private long nSen;


		public PopulationReportDto(LocalDate dateStat, Integer lienBanque, long nFam, long nPers, long nNour, long nBebe,
				long nEnf, long nAdo, long n1824, long nSen) {
			super();
			this.dateStat = dateStat;
			this.lienBanque = lienBanque;
			this.nFam = nFam;
			this.nPers = nPers;
			this.nNour = nNour;
			this.nBebe = nBebe;
			this.nEnf = nEnf;
			this.nAdo = nAdo;
			this.n1824 = n1824;
			this.nSen = nSen;
		}


		


		public LocalDate getDateStat() {
			return dateStat;
		}





		public void setDateStat(LocalDate dateStat) {
			this.dateStat = dateStat;
		}





		public Integer getLienBanque() {
			return lienBanque;
		}





		public void setLienBanque(Integer lienBanque) {
			this.lienBanque = lienBanque;
		}





		public long getnFam() {
			return nFam;
		}


		public void setnFam(long nFam) {
			this.nFam = nFam;
		}


		public long getnPers() {
			return nPers;
		}


		public void setnPers(long nPers) {
			this.nPers = nPers;
		}


		public long getnNour() {
			return nNour;
		}


		public void setnNour(long nNour) {
			this.nNour = nNour;
		}


		public long getnBebe() {
			return nBebe;
		}


		public void setnBebe(long nBebe) {
			this.nBebe = nBebe;
		}


		public long getnEnf() {
			return nEnf;
		}


		public void setnEnf(long nEnf) {
			this.nEnf = nEnf;
		}


		public long getnAdo() {
			return nAdo;
		}


		public void setnAdo(long nAdo) {
			this.nAdo = nAdo;
		}


		public long getN1824() {
			return n1824;
		}


		public void setN1824(long n1824) {
			this.n1824 = n1824;
		}


		public long getnSen() {
			return nSen;
		}


		public void setnSen(long nSen) {
			this.nSen = nSen;
		}
	    
}
