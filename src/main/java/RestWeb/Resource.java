package RestWeb;

import RestBean.BeanJson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import myRESTwsBean.BeanJSon;

@Path("")
public class Resource {
    @Path("/json/{resourceName}")
    @GET
    //Si volem XML tmb es pot
    @Produces(MediaType.APPLICATION_JSON)
    public String getResourceInfo(@PathParam("resourceName") String resourceName){
        //TODO get information about a feel, keeped into de DB
        return null;
    }

    @Path("/post")
    @POST
    public Response crearJSon(BeanJson bean){
        String resultat = bean.getNom();
        return Response.status(201).entity(resultat).build();
    }
}
