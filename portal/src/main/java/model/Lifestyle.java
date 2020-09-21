package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="LIFESTYLE")
public class Lifestyle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lifestyleid;
    private String religion;
    private String alcohol;
    private String smoking;
    private String drugs;
    private String kids;
   @OneToOne(mappedBy = "lifestyleid")
    private Portaluser portaluser;
    @OneToOne(mappedBy = "lifestyleid")
    private Preferences preferences;


    public Lifestyle(){}

    public Lifestyle(String religion,String alcohol,String smoking,String drugs,String kids){
        setReligion(religion);
        setAlcohol(alcohol);
        setSmoking(smoking);
        setDrugs(drugs);
        setKids(kids);
    }

    public int getLifestyleid() {
        return lifestyleid;
    }

    public void setLifestyleid(int lifestyleid) {
        this.lifestyleid = lifestyleid;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }


    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }


    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    @Override
    public String toString() {
        return "Lifestyle{" +
                "lifestyleid=" + lifestyleid +
                ", religion='" + religion + '\'' +
                ", alcohol='" + alcohol + '\'' +
                ", smoking='" + smoking + '\'' +
                ", drugs='" + drugs + '\'' +
                ", kids='" + kids + '\'' +
                '}';
    }
}
