package foodbank.it.web.dto;


public class FunctionDto {
    private int funcId;
    private String fonctionName;
    private String fonctionNameNl;
    private String definitionFonction;
    private boolean actif;
    private String rem;
    private Short lienBanque;
    private Integer lienDis;


    public FunctionDto(int funcId, String fonctionName, String fonctionNameNl, String definitionFonction, boolean actif,
                       String rem, Short lienBanque, Integer lienDis) {
        this.funcId = funcId;
        this.fonctionName = fonctionName;
        this.fonctionNameNl = fonctionNameNl;
        this.definitionFonction = definitionFonction;
        this.actif = actif;
        this.rem = rem;
        this.lienBanque = lienBanque;
        this.lienDis = lienDis;

    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public String getFonctionName() {
        return fonctionName;
    }

    public void setFonctionName(String fonctionName) {
        this.fonctionName = fonctionName;
    }

    public String getFonctionNameNl() {
        return fonctionNameNl;
    }

    public void setFonctionNameNl(String fonctionNameNl) {
        this.fonctionNameNl = fonctionNameNl;
    }

    public String getDefinitionFonction() {
        return definitionFonction;
    }

    public void setDefinitionFonction(String definitionFonction) {
        this.definitionFonction = definitionFonction;
    }

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public Short getLienBanque() {
        return lienBanque;
    }

    public void setLienBanque(Short lienBanque) {
        this.lienBanque = lienBanque;
    }

    public Integer getLienDis() {
        return lienDis;
    }

    public void setLienDis(Integer lienDis) {
        this.lienDis = lienDis;
    }


}
