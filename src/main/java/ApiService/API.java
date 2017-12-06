package ApiService;

import RestBean.BeanJson;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import myRESTwsBean.BeanJSon;

/*-----API DEFINITION >>>>>>>> https://app.swaggerhub.com/apis/HumbertValles/MyTube/1.0.0-----*/

@Path("")
public class API {
    /*-------SERVER-------*/


    /*-------CONTENT-------*/

    /*-------USER-------*/

    @Path("/user/sign")
    @POST
    public Response signUser(BeanJson bean){
        /*String resultat = bean.getNom();
        return Response.status(201).entity(resultat).build();*/
        return null;
    }

    @Path("/user/{userID}/content")
    @GET
    //Si volem XML tmb es pot
    @Produces(MediaType.APPLICATION_JSON)
    public String contentFromUser(@PathParam("userID") String userID){
        return null;
    }

    @Path("/user/login")
    @POST
    public Response logUser(BeanJson bean){
        /*String resultat = bean.getNom();
        return Response.status(201).entity(resultat).build();*/
        return null;
    }

    @Path("/user/{userID}")
    @GET
    //Si volem XML tmb es pot
    @Produces(MediaType.APPLICATION_JSON)
    public String showUserInformation(@PathParam("userID") String userID){
        return null;
    }


}
