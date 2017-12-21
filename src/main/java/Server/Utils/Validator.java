package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.ServerBO;

import java.util.ArrayList;

public class Validator {

    public static int checkServerCredentials(String ip, String port, String serversRegistryJson){
        ServerBO[] serverBOS = Server.Utils.Parser.jsonServerToArray(serversRegistryJson);

        for(ServerBO serverBO: serverBOS){
            if(serverBO.getHost().equals(ip) && Integer.toString(serverBO.getPort()).equals(port)){
                return serverBO.getId();
            }
        }

        return -1;
    }

    public static ArrayList<ContentBO> selectWantedContent(String contentStringJson, String keyWord){
        ContentBO[] contentBOS = Server.Utils.Parser.jsonContentToArray(contentStringJson);
        ArrayList<ContentBO> desiredContent = new ArrayList<>();

        for(ContentBO contentBO:contentBOS){
            if(contentBO.getDescription().contains(keyWord) || contentBO.getTitle().contains(keyWord)){
                desiredContent.add(contentBO);
            }
        }

        return desiredContent;
    }

    public static int selectSpecificContent(String contentStringJson, String title, int idUser, int idServer){
        ContentBO[] contentBOS = Server.Utils.Parser.jsonContentToArray(contentStringJson);

        for(ContentBO contentBO:contentBOS){
            if(contentBO.getTitle().equals(title) && contentBO.getServerId() == idServer && contentBO.getUploader() == idUser){
                return contentBO.getId();
            }
        }

        return -1;
    }
}
