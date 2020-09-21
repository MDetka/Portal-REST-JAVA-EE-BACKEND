package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EDUCATION")
public class Education implements Serializable {
    private static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int educationid;
    private String education;
    private String proffesion;
    @OneToOne(mappedBy = "educationid")
    private Portaluser portaluser;


    public Education(){

    }

    public Education(String e,String p){
        setEducation(e);
        setProffesion(p);
    }
    public int getEducationid() {
        return educationid;
    }

    public void setEducationid(int educationid) {
        this.educationid = educationid;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    @Override
    public String toString() {
        return "Education{" +
                "educationid=" + educationid +
                ", education='" + education + '\'' +
                ", proffesion='" + proffesion + '\'' +
                '}';
    }
}
