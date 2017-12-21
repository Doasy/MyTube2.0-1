package Client.Utils;

import ClassesBO.ContentBO;
import ClassesBO.UserBO;
import com.google.gson.Gson;

public class Parser {

    public static String getTitleFromPath(String contentPath){
        String[] splitedPath = contentPath.split("/");

        return splitedPath[splitedPath.length-1];
    }

    static UserBO[] jsonUsersToArray(String usersRegistryJson){
        Gson gson = new Gson();

        return gson.fromJson(usersRegistryJson, UserBO[].class);
    }

    static ContentBO[] jsonContentToArray(String contentStringJson){
        Gson gson = new Gson();
        return gson.fromJson(contentStringJson, ContentBO[].class);
    }

    public static int getIdUser(String userStringJson){
        Gson gson = new Gson();
        UserBO[] userBO = gson.fromJson(userStringJson, UserBO[].class);
        return userBO[0].getId();
    }

    static String userToJsonRegister(String userName, String password){
        Gson gson = new Gson();
        UserBO userBO = new UserBO();

        userBO.setUsername(userName);
        userBO.setPassword(password);

        return gson.toJson(userBO);
    }
}
