package rest;

import ejb.LoginEJB;
import model.Portaluser;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

//@RequestScoped
@Path("/login")
public class LoginRest {
    @EJB
    private LoginEJB loginEJB;

    @Context
    private HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @FormParam("email") String email,
            @FormParam("password") String password) {
        Portaluser user = loginEJB.authenticate(email, password);
        if (user != null) {
            return Response.status(Response.Status.OK).entity(user).type(MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
