package ejb;

import model.Photo;
import model.Portaluser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@Stateless
public class PhotoEJB {

    @PersistenceContext
    private EntityManager em;
    @Context
    private HttpServletRequest request;

    public String uploadPhoto(String title, String about) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);
            String path = "/assets/img/users/" + Integer.toString(id);
            Photo photo = new Photo(title, about, path +"/"+title);
            em.persist(photo);
            user.addPhoto(photo);
            em.merge(user);
            em.flush();
            return path;
        }
        return "smth wrong";
    }

    public String removePhoto(int photoid) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);
            Photo photo = em.find(Photo.class, photoid);
            String temp = photo.getPhotopath();
            user.removePhoto(photo);
            em.remove(photo);
            em.merge(user);
            em.flush();
            return temp;
        } return "smth wrong";
    }
}
