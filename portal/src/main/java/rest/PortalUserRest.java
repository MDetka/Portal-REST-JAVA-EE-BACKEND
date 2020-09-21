package rest;


import ejb.PortaluserEJB;
import model.Portaluser;
import javax.enterprise.context.RequestScoped;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Named("users")
//@RequestScoped
@Path("/portalusers")
public class PortalUserRest {
    @EJB
    private PortaluserEJB portaluserEJB ;

    public PortalUserRest(){}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPortaluser(){

        return Response.status(Response.Status.OK).entity(portaluserEJB.getAllPortalusers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Portaluser getUser(@PathParam("id") int id){
        return portaluserEJB.getUser(id);
    }

    @GET
    @Path("/gender/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Portaluser> getAllByGender(@PathParam("gender") String gender){
        return portaluserEJB.getAllByGender(gender);
    }
    @GET
    @Path("/recommended")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecommended(){
        return Response.status(Response.Status.OK).entity(portaluserEJB.getRecommendedPortalusers()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Portaluser addPortalUser(Portaluser portaluser) {
        return portaluserEJB.addPortalUser(portaluser);

    }

    @DELETE
    @Path("/{uId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePortalUser(@PathParam("uId") int id){
        return portaluserEJB.removePortalUser(id);
    }

}
