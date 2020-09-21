package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "PHOTO")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int photoid;
    private String title;
    private String about;
    private String photopath;

    public Photo(){
    }
    public Photo(String title,String about,String photopath){
        setTitle(title);
        setAbout(about);
        setPhotopath(photopath);
    }

    public int getPhotoid() {
        return photoid;
    }

    public void setPhotoid(int photoid) {
        this.photoid = photoid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "photoid=" + photoid +
                ", title='" + title + '\'' +
                ", about='" + about + '\'' +
                ", photopath='" + photopath + '\'' +

                '}';
    }
}
