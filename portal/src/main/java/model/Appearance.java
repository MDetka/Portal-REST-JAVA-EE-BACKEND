package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APPEARANCE")
public class Appearance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appearanceid;
    private String eyecolor;
    private String haircolor;
    private String hairlength;
    private String figure;
    private String heigth;
    @OneToOne(mappedBy = "appearanceid")
    private Portaluser portaluser;
    @OneToOne(mappedBy = "appearanceid")
    private Preferences preferences;

    public Appearance(){

    }
    public Appearance(String eyecolor,String haircolor,String hairlength,String figure, String heigth){
        setEyecolor(eyecolor);
        setHaircolor(haircolor);
        setHairlength(hairlength);
        setFigure(figure);
        setHeigth(heigth);

    }
    public int getAppearanceid() {
        return appearanceid;
    }

    public void setAppearanceid(int appearanceid) {
        this.appearanceid = appearanceid;
    }

    public String getEyecolor() {
        return eyecolor;
    }

    public void setEyecolor(String eyecolor) {
        this.eyecolor = eyecolor;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public String getHairlength() {
        return hairlength;
    }

    public void setHairlength(String hairlength) {
        this.hairlength = hairlength;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return "Appearance{" +
                "appearanceid=" + appearanceid +
                ", eyecolor='" + eyecolor + '\'' +
                ", haircolor='" + haircolor + '\'' +
                ", hairlength='" + hairlength + '\'' +
                ", figure='" + figure + '\'' +
                ", heigth='" + heigth + '\'' +
                '}';
    }
}
