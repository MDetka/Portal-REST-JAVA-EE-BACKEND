package ejb;

import model.Friendship;
import model.Portaluser;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class FriendshipEJB {

    @PersistenceContext
    private EntityManager em;
    @Context
    private HttpServletRequest request;

    public FriendshipEJB() {

    }
    public Response addFriend(int userid) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);
            Portaluser friend = em.find(Portaluser.class, userid);
            Friendship friendship = new Friendship();
            if (user == null)
                return Response.status(Response.Status.NOT_FOUND).entity("Uzytkownik nie zalogowany").build();
            if (friend == null)
                return Response.status(Response.Status.NOT_FOUND).entity("Uzytkownik nie znaleziony" + userid).build();
            friendship.setFriendRequester(user);
            friendship.setFriendReceiver(friend);
            em.persist(friendship);
            em.flush();
            user.addRequestedFriend(friendship);
            friend.addReceivedFriend(friendship);
            em.merge(user);
            em.merge(friend);
            em.flush();
            return Response.status(Response.Status.OK).entity(friendship).type(MediaType.APPLICATION_JSON).build();

        }
        return null;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response removeFriend(int friendshipid) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Friendship friendship = em.find(Friendship.class,friendshipid);
            if(friendship != null) {
                Portaluser user = em.find(Portaluser.class, friendship.getFriendReceiver().getId());
                Portaluser friend = em.find(Portaluser.class, friendship.getFriendRequester().getId());
                if (user.getId() == id || friend.getId() == id) {
                    user.removeReceivedFriend(friendship);
                    friend.removeRequestedFriend(friendship);
                    em.merge(user);
                    em.merge(friend);
                    em.remove(friendship);
                    em.flush();
                    return Response.status(Response.Status.OK).entity("przyjaciel usunięty").type(MediaType.APPLICATION_JSON).build();
                }else{
                    return Response.status(Response.Status.FORBIDDEN).entity("Forbidden acction").type(MediaType.APPLICATION_JSON).build();

                }
            }
            else
                return Response.status(Response.Status.NOT_FOUND).entity("przyjaciel nie istnieje").type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Uzytkownik nie zalogowany").build();
    }

    public Response getMyFriends() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            TypedQuery<Friendship> query = em.createQuery("from Friendship f where f.friendReceiver="+id+" or f.friendRequester="+id,Friendship.class);
            List<Friendship> userlist = query.getResultList();
            return Response.status(Response.Status.OK).entity(userlist).build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity("Uzytkownik nie zalogowany").build();
    }
}
