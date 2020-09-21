package model;

import com.fasterxml.jackson.annotation.JsonMerge;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="preferences")
public class Preferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int preferencesid;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Appearance appearanceid;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Lifestyle lifestyleid;

    @OneToOne(mappedBy = "preferencesid")
    private Portaluser portaluser;

    public Preferences(){}

    public Preferences(Appearance appearance,Lifestyle lifestyle){
    setAppearanceid(appearance);
    setLifestyleid(lifestyle);

    }

    public int getPreferencesid() {
        return preferencesid;
    }

    public void setPreferencesid(int preferencesid) {
        this.preferencesid = preferencesid;
    }

    public Appearance getAppearanceid() {
        return appearanceid;
    }

    public void setAppearanceid(Appearance appearanceid) {
        this.appearanceid = appearanceid;
    }

    public Lifestyle getLifestyleid() {
        return lifestyleid;
    }

    public void setLifestyleid(Lifestyle lifestyleid) {
        this.lifestyleid = lifestyleid;
    }

}
