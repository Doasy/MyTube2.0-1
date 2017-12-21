package Server.Utils;

import Server.ClassesBO.ServerBO;
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
}
