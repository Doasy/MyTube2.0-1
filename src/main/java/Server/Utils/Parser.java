package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.ServerBO;
import ClassesBO.UserBO;
import WebService.ApiService.User;
import com.google.gson.Gson;

public class Parser {

    /*------SERVER------*/

    public static ServerBO[] jsonServerToArray(String serversStringJson){
        Gson gson = new Gson();

        return gson.fromJson(serversStringJson, ServerBO[].class);
    }

    public static String serverToJsonRegister(String ip, String port){
        Gson gson = new Gson();
        ServerBO serverBO = new ServerBO();

        serverBO.setHost(ip);
        serverBO.setPort(Integer.parseInt(port));

        return gson.toJson(serverBO);
    }

    /*-----CONTENT------*/

    public static ContentBO jsonContentToContent(String contentStringJson){
        Gson gson = new Gson();
        return gson.fromJson(contentStringJson, ContentBO.class);
    }

    public static ContentBO[] jsonContentToArray(String contentStringJson){
        Gson gson = new Gson();
        return gson.fromJson(contentStringJson, ContentBO[].class);
    }
    public static String contentToJsonRegister(String serverId, String userId, String title, String description){
        Gson gson = new Gson();
        ContentBO contentBO = new ContentBO();

        contentBO.setServerId(Integer.parseInt(serverId));
        contentBO.setUploader(Integer.parseInt(userId));
        contentBO.setTitle(title);
        contentBO.setDescription(description);

        return gson.toJson(contentBO);
    }

    public static String contentToJsonRegister(String title, String description){
        Gson gson = new Gson();
        ContentBO contentBO = new ContentBO();
        contentBO.setTitle(title);
        contentBO.setDescription(description);

        return gson.toJson(contentBO);
    }


    /*------USER------*/
    public static UserBO jsonUserToUser(String userStringJson){
        Gson gson = new Gson();
        return gson.fromJson(userStringJson, UserBO.class);
    }

    public static UserBO[] jsonUserToArray(String userStringJson){
        Gson gson = new Gson();
        return gson.fromJson(userStringJson, UserBO[].class);
    }


}
