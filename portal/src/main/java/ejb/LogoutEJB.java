package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Stateless
public class LogoutEJB {
    @PersistenceContext
    private EntityManager em;

    @Context
    private HttpServletRequest request;

    public Response logout(){
        HttpSession session = request.getSession(false);
        if(session==null){
            return  Response.status(Response.Status.NO_CONTENT).entity("Brak zalogowanego użytkownika").build();
        }
        session.invalidate();
        session=null;
        return Response.status(Response.Status.OK).entity("Wylogowano").build();
    }


}
