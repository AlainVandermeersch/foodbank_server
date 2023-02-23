package foodbank.it.persistence.model;

import java.math.BigDecimal;

public class OrgClientsCount {
    private long nbClients;
    private long  nFam;

    private long  nPers;

    private long  nNour;

    private long  nBebe;

    private long  nEnf;

    private long  nAdo;

    private long  n1824;
    private long  nSen;
    private BigDecimal nEq;

    public OrgClientsCount(long nbClients, long nFam, long nPers, long nNour, long nBebe, long nEnf, long nAdo, long n1824, long nSen, BigDecimal nEq) {
        this.nbClients = nbClients;
        this.nFam = nFam;
        this.nPers = nPers;
        this.nNour = nNour;
        this.nBebe = nBebe;
        this.nEnf = nEnf;
        this.nAdo = nAdo;
        this.n1824 = n1824;
        this.nSen = nSen;
        this.nEq = nEq;
    }

    public long getNbClients() {
        return nbClients;
    }

    public void setNbClients(long nbClients) {
        this.nbClients = nbClients;
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

    public BigDecimal getnEq() {
        return nEq;
    }

    public void setnEq(BigDecimal nEq) {
        this.nEq = nEq;
    }
}

