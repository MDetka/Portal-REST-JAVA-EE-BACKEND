package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Table(name="portaluser")
public class  Portaluser extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Preferences preferencesid;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Appearance appearanceid;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Lifestyle lifestyleid;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Education educationid;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Account accountid;
    private String localization;
    @JsonIgnore
    private String about;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Portaluser")
     private List<Photo> photos = new ArrayList<Photo>() ;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "friendRequester",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friendship> requestedFriends = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "friendReceiver",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friendship> receivedFriends = new ArrayList<>();




    public List<Friendship> getRequestedFriends() {
        return requestedFriends;
    }

    public void setRequestedFriends(List<Friendship> requestedFriends) {
        this.requestedFriends = requestedFriends;
    }

    public List<Friendship> getReceivedFriends() {
        return receivedFriends;
    }

    public void setReceivedFriends(List<Friendship> receivedFriends) {
        this.receivedFriends = receivedFriends;
    }



    public Portaluser(){}
    public Portaluser(String name, Date dob, String email,String about, String phone, char gender, String password, String localization,Appearance appearance,Lifestyle lifestyle,Education education,Preferences preferences,Account account){
        super(name,dob,email,phone,gender,password);
    setAbout(about);
    setPreferencesid(preferences);
    setAccountid(account);
    setAppearanceid(appearance);
    setLifestyleid(lifestyle);
    setEducationid(education);

    setLocalization(localization);
    addPhoto(new Photo("Title","About","/portalusers/default.jpg"));
    }

    public Account getAccountid() {
        return accountid;
    }

    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    public Preferences getPreferencesid() {
        return preferencesid;
    }

    public void setPreferencesid(Preferences preferencesid) {
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

    public Education getEducationid() {
        return educationid;
    }

    public void setEducationid(Education educationid) {
        this.educationid = educationid;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }
public void addRequestedFriend(Friendship friend){
        requestedFriends.add(friend);
}
public void addReceivedFriend(Friendship friend){
        receivedFriends.add(friend);
}
    public void removeRequestedFriend(Friendship friend){
        requestedFriends.remove(friend);
    }
    public void removeReceivedFriend(Friendship friend){
        receivedFriends.remove(friend);
    }
    public void addPhoto(Photo photo){
        photos.add(photo);
    }
    public void removePhoto(Photo photo){
        photos.remove(photo);
    }
    public List<Photo> getPhotos() {
       return photos;
    }
    public void setPhotos(List<Photo> photos) {
    this.photos = photos;
}

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Portaluser{" +
                "preferencesid=" + preferencesid +
                ", appearanceid=" + appearanceid +
                ", lifestyleid=" + lifestyleid +
                ", educationid=" + educationid +
                ", accountid=" + accountid +
                ", localization='" + localization + '\'' +
          //      ", about='" + about + '\'' +
          //      ", photos=" + photos +
          //      ", requestedFriends=" + requestedFriends +
          //      ", receivedFriends=" + receivedFriends +
                '}';
    }
}
