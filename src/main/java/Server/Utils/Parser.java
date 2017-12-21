package Server.Utils;

import Client.ClassesBO.ServerBO;
import com.google.gson.Gson;

public class Parser {

    public static ServerBO[] jsonServerToArray(String serversStringJson){
        Gson gson = new Gson();

        return gson.fromJson(serversStringJson, ServerBO[].class);

    }
}
