package WebService.ApiService;

import WebService.RestBean.BeanJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class User {

    @Path("/user/new")
    @POST
    public Response signUser(BeanJson bean) {
        /*String resultat = bean.getNom();
        return Response.status(201).entity(resultat).build();*/
        return null;
    }

    @Path("/user/{userID}/content")
    @GET
    //Si volem XML tmb es pot
    @Produces(MediaType.APPLICATION_JSON)
    public String contentFromUser(@PathParam("userID") String userID) {
        return null;
    }

    @Path("/user/{userID}")
    @GET
    //Si volem XML tmb es pot
    @Produces(MediaType.APPLICATION_JSON)
    public String showUserInformation(@PathParam("userID") String userID) {
        return null;
    }
}
