package model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FRIENDSHIP")
public class Friendship implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int friendshipID;
    @JsonIgnore
    @ManyToOne
    Portaluser friendRequester;
    @JsonIgnore
    @ManyToOne
    Portaluser friendReceiver;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "friendship",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<Message>() ;


    public int getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(int friendshipID) {
        this.friendshipID = friendshipID;
    }

    public Portaluser getFriendRequester() {
        return friendRequester;
    }

    public void setFriendRequester(Portaluser friendRequester) {
        this.friendRequester = friendRequester;
    }

    public Portaluser getFriendReceiver() {
        return friendReceiver;
    }

    public void setFriendReceiver(Portaluser friendReceiver) {
        this.friendReceiver = friendReceiver;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public void addMessage(Message message){
        message.setFriendship(this);
        messages.add(message);

    }
}
