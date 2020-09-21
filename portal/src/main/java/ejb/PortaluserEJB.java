package ejb;
import model.*;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Port;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

@Stateless
public class PortaluserEJB implements Serializable {
    private static final long serialVersionUID = 1L;


    @PersistenceContext
    private EntityManager em;

    @Context
    private HttpServletRequest request;

    public PortaluserEJB(){
    }

    public List<Portaluser> getAllPortalusers(){
            TypedQuery<Portaluser> query = em.createQuery("from Portaluser ",Portaluser.class);
            List<Portaluser> userlist = query.getResultList();
            return userlist;
    }

    public List<Portaluser> getRecommendedPortalusers(){
        Map<Portaluser,Integer> recomap = new HashMap<Portaluser,Integer>();
        HttpSession session = request.getSession(false);

        TypedQuery<Portaluser> query = null;
        List<Portaluser> userlist = null;

        if (session!=null) {
            int id = (int) session.getAttribute("userId");
            Portaluser user = em.find(Portaluser.class, id);


            if (user.getGender() == 'm') {
                query = em.createQuery("from Portaluser where gender = 'f'", Portaluser.class);
                userlist = query.getResultList();
            }
            if (user.getGender() == 'f') {
                query = em.createQuery("from Portaluser where gender = 'm'", Portaluser.class);
                userlist = query.getResultList();
            }

            if (userlist != null) {
                for (Portaluser portaluser : userlist) {
                    Integer result = 0;
                    //appearance
                    if(user.getPreferencesid().getAppearanceid().getEyecolor().equals(portaluser.getAppearanceid().getEyecolor())){
                        result += 10;
                    }
                    if(user.getPreferencesid().getAppearanceid().getFigure().equals(portaluser.getAppearanceid().getFigure())){
                        result +=10;
                    }
                    if(user.getPreferencesid().getAppearanceid().getHaircolor().equals(portaluser.getAppearanceid().getHaircolor())){
                        result +=10;
                    }
                    if(user.getPreferencesid().getAppearanceid().getHairlength().equals(portaluser.getAppearanceid().getHairlength())){
                        result+=10;
                    }
                    //LIFESTYLE
                    if(user.getPreferencesid().getLifestyleid().getReligion().equals("Obojętne")){
                        result+=5;
                    }else {
                        if(user.getPreferencesid().getLifestyleid().getReligion().equals(portaluser.getLifestyleid().getReligion())){
                            result+=10;
                        }
                    }
                    // HEIgth
                    int diff = Integer.parseInt(user.getPreferencesid().getAppearanceid().getHeigth())-Integer.parseInt(portaluser.getAppearanceid().getHeigth());
                    if(diff>0 && diff<=5){
                        result+=10;
                    }
                    else if(diff >5 && diff <= 10){
                        result+=5;
                    }
                    else if(Integer.parseInt(user.getPreferencesid().getAppearanceid().getHeigth())==205 && Integer.parseInt(portaluser.getAppearanceid().getHeigth())>200){
                        result+=10;
                    }



                    if(user.getPreferencesid().getLifestyleid().getAlcohol().equals("Obojętne")){
                        result+=5;
                    }else{
                        if(user.getPreferencesid().getLifestyleid().getAlcohol().equals(portaluser.getLifestyleid().getAlcohol())){
                            result+=10;
                        }
                    }
                    if(user.getPreferencesid().getLifestyleid().getSmoking().equals("Obojętne")){
                        result+=5;
                    }else {
                        if(user.getPreferencesid().getLifestyleid().getSmoking().equals(portaluser.getLifestyleid().getSmoking())){
                            result+=10;
                        }
                    }

                    if(user.getPreferencesid().getLifestyleid().getDrugs().equals("Obojętne")){
                            result+=5;
                    }else{
                        if(user.getPreferencesid().getLifestyleid().getDrugs().equals(portaluser.getLifestyleid().getDrugs())){
                            result+=10;
                        }
                    }
                    if(user.getPreferencesid().getLifestyleid().getKids().equals("Obojętne")){
                        result+=5;
                    }
                    else {
                        if (user.getPreferencesid().getLifestyleid().getKids().equals(portaluser.getLifestyleid().getKids())) {
                            result += 10;
                        }
                    }

                    recomap.put(portaluser,result);
                    result=0;
                }
                Map<Portaluser,Integer> sorted = recomap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                ArrayList<Portaluser> valueList = new ArrayList<Portaluser>(sorted.keySet());


                System.out.println(sorted);
                return valueList;
            } else {
                return null;
            }


    }else {
            return null;
        }
    }

    public Portaluser getUser(int id){
            return em.find(Portaluser.class, id);
    }


    public List<Portaluser> getAllByGender(String usergender){
        List<Portaluser> userlist = null;

        char c = usergender.charAt(0);
            TypedQuery<Portaluser> query = em.createQuery("from Portaluser where gender=:gender ",Portaluser.class);
            query.setParameter("gender",c);
            userlist = query.getResultList();

        return userlist;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Portaluser addPortalUser(Portaluser user){

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Timestamp ts2 ;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,1);
        ts2 = new Timestamp(cal.getTime().getTime());
        Account acc = new Account("standard",new Date(ts.getTime()),new Date(ts2.getTime()));
        user.setAccountid(acc);
        em.persist(user);
        em.flush();
        return user;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response removePortalUser(int id){
        Portaluser user = em.find(Portaluser.class,id);
        List <Friendship> recvd = user.getReceivedFriends();
        List <Friendship> reqtd = user.getRequestedFriends();
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        for (Friendship f:recvd) {
            list.add(f.getFriendshipID());

        }
        for (Friendship f:reqtd) {
            list2.add(f.getFriendshipID());
        }
        for(Integer i:list){
            Query query = em.createQuery("Delete Message m where friendship="+i);
            query.executeUpdate();
            Query query2 = em.createQuery("Delete Friendship f where friendshipID="+i);
            query2.executeUpdate();
        }
        for(Integer i:list2){
            Query query = em.createQuery("Delete Message m where friendship="+i);
            query.executeUpdate();
            Query query2 = em.createQuery("Delete Friendship f where friendshipID="+i);
            query2.executeUpdate();
        }
        em.flush();
        em.clear();
        em.getTransaction().commit();
           user = em.find(Portaluser.class,id);
           em.remove(user);
        em.flush();
        return Response.status(Response.Status.OK).entity("usunieto").type(MediaType.TEXT_PLAIN).build();
    }
}
