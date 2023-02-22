package foodbank.it.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name="birbcat")
public class BirbCat implements Serializable {
    protected static final String PK = "birbId";
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="birb_ID", unique=true, nullable=false, precision=10)
    private int birbId;
    @Column(name="cat_name")
    private String catName;
    @Column(name="cat_name_nl")
    private String catNameNl;
    @Column(name="cpas_cat")
    private String cpasCat;
    @Column(name="cat_note")
    private String catNote;
    @Column(name="deleted")
    private int deleted;

    public int getBirbId() {
        return birbId;
    }

    public void setBirbId(int birbId) {
        this.birbId = birbId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatNameNl() {
        return catNameNl;
    }

    public void setCatNameNl(String catNameNl) {
        this.catNameNl = catNameNl;
    }

    public String getCpasCat() {
        return cpasCat;
    }

    public void setCpasCat(String cpasCat) {
        this.cpasCat = cpasCat;
    }

    public String getCatNote() {
        return catNote;
    }

    public void setCatNote(String catNote) {
        this.catNote = catNote;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirbCat birbCat = (BirbCat) o;
        return birbId == birbCat.birbId && deleted == birbCat.deleted && Objects.equals(catName, birbCat.catName) && Objects.equals(catNameNl, birbCat.catNameNl) && Objects.equals(cpasCat, birbCat.cpasCat) && Objects.equals(catNote, birbCat.catNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birbId, catName, catNameNl, cpasCat, catNote, deleted);
    }

    @Override
    public String toString() {
        return "BirbCat{" +
                "birbId=" + birbId +
                ", catName='" + catName + '\'' +
                ", catNameNl='" + catNameNl + '\'' +
                ", cpasCat='" + cpasCat + '\'' +
                ", catNote='" + catNote + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
