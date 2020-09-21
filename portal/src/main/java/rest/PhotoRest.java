package rest;

import ejb.LoginEJB;
import ejb.PhotoEJB;
import model.Portaluser;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

//@RequestScoped
@Path("/photo")
public class PhotoRest {
    @EJB
    private PhotoEJB photoEJB;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadPhoto(@FormDataParam("file")InputStream uIS,
                                @FormDataParam("file")FormDataContentDisposition fileDetails){

        if(uIS==null || fileDetails == null)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Invalid form data").build();


        String path = "D:/angular/projekt-portal-randkowy-master/src"+photoEJB.uploadPhoto(fileDetails.getFileName(),"about");
        try{
            createFolderIfNotExists(path);

        }catch (SecurityException e){
            e.printStackTrace();
            return Response.status(Response.Status.fromStatusCode(500)).entity("Cannot create folder").build();
        }
        saveToFile(uIS,path+"/"+fileDetails.getFileName());
        return Response.status(Response.Status.OK).entity("Uploaded").build();
    }


    @DELETE
    @Path("/{pId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePhoto(@PathParam("pId") int id){
         String temp = photoEJB.removePhoto(id);
         deleteFile(temp);
         return Response.status(Response.Status.OK).entity("photo removed").build();

    }






    private boolean deleteFile(String path)throws SecurityException{
        File file = new File("D:/angular/projekt-portal-randkowy-master/src/"+path);
        if(file.delete()){
            System.out.println("D:/angular/projekt-portal-randkowy-master/src/"+path);
            return true;
        }
        return false;

    }


    private boolean createFolderIfNotExists(String path)throws SecurityException{
        File file = new File(path);
        if(!file.exists()) {

            if(file.mkdirs()){
            System.out.println("STWORZONO " + path);
            return true;
        }else {
                System.out.println("Nie STWORZONO"+path);
                return false;
            }
        }
        return true;
    }
    private void saveToFile(InputStream inStream,String path){
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        try {
            out = new FileOutputStream(new File(path));
            while ((read = inStream.read(bytes))!=-1){
                out.write(bytes,0,read);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}


