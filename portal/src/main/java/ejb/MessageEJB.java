package ejb;

import model.Friendship;
import model.Message;
import model.Portaluser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Port;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Stateless
public class MessageEJB {

    @PersistenceContext
    private EntityManager em;
    @Context
    private HttpServletRequest request;

    public MessageEJB() {
    }

    public Response sendMessage(String messagetext, int friendshipid) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);
            Message message = new Message();
            message.setMessage(user.getName() + ": " + messagetext);
            Friendship friendship = em.find(Friendship.class, friendshipid);
            message.setFriendship(friendship);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            message.setMessagedate(new Date(ts.getTime()));
            em.persist(message);
            em.flush();
            return Response.status(Response.Status.OK).entity(message).build();
        } else return Response.status(Response.Status.FORBIDDEN).entity("Uzytkownik nie zalogwany").build();
    }

    public Response getMessages(int friendshipid) {
        List<Message> messages = null;
        Friendship f = em.find(Friendship.class, friendshipid);
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);
            if (user.getId() == f.getFriendRequester().getId() || user.getId() == f.getFriendReceiver().getId()) {
                Query query = em.createQuery("from Message m where m.friendship = " + friendshipid, Message.class);
                messages = query.getResultList();
                return Response.status(Response.Status.OK).entity(messages).type(MediaType.APPLICATION_JSON_TYPE).build();
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
