package rest;

import ejb.MessageEJB;
import model.Message;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@RequestScoped
@Path("/message")
public class MessageRest {
    @EJB
    MessageEJB messageEJB;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(@FormParam("message") String message,@FormParam("friendshipid")int id){
        return messageEJB.sendMessage(message,id);
    }
    @GET
    @Path("/read/{fId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(@PathParam("fId") int fid){
        return messageEJB.getMessages(fid);
    }
}
