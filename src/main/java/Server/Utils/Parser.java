package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.ServerBO;
import com.google.gson.Gson;

public class Parser {

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

    public static ContentBO[] jsonContentToArray(String contentStringJson){
        Gson gson = new Gson();
        return gson.fromJson(contentStringJson, ContentBO[].class);
    }
}
