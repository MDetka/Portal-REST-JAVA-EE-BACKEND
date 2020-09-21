package rest;


import ejb.LogoutEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

//@RequestScoped
@Path("/logout")
public class LogoutRest {

    @EJB
    LogoutEJB logoutEJB;

    @GET
    public Response Logout(){
            return logoutEJB.logout();
    }
}
