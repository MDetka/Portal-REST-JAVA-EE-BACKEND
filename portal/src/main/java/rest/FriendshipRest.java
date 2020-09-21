package rest;

import ejb.FriendshipEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@RequestScoped
@Path("/friendship")
public class FriendshipRest {
    @EJB
    private FriendshipEJB friendshipEJB;

    @POST
    @Path("/add/{id}")
    public Response addFriend(@PathParam("id") int id) {
        return friendshipEJB.addFriend(id);
    }
    @DELETE
    @Path("/remove/{id}")
    public Response removeFriend(@PathParam("id") int id) {
        return friendshipEJB.removeFriend(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyFriends(){
        return friendshipEJB.getMyFriends();
    }

}

