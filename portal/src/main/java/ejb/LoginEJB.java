package ejb;

import model.Portaluser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import java.util.List;

@Stateless
public class LoginEJB {

    @PersistenceContext
    private EntityManager em;
    @Context
    private HttpServletRequest request;

    public Portaluser authenticate(String email,String password){
        List<Portaluser> users;
        TypedQuery<Portaluser> query = em.createQuery("FROM Portaluser WHERE email=:email",Portaluser.class);
        Portaluser user;
        query.setParameter("email",email);
        users = query.getResultList();
        if(!users.isEmpty()) {
            user = users.get(0);
            em.merge(user);

             em.flush();
            if (user != null) {
                if (password.equals(user.getPassword())) {

                    HttpSession session = request.getSession();
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("accountType", user.getAccountid().getType());
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
