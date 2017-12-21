package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.UserBO;
import sun.awt.KeyboardFocusManagerPeerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class DBGets extends DBGenerics{
    private static final String SHOW_ALL_CONTENTS_URL = GENERIC_URL + "content/";
    private static final String SHOW_ALL_SERVERS_URL = GENERIC_URL + "server/";
    private static final String SHOW_ALL_USERS_URL = GENERIC_URL + "user/";


    public static String getAllUsers() {
        String usersString = get(SHOW_ALL_USERS_URL);
        return usersString;
    }

    public static String getUserByID(String id){
        String userString = get(SHOW_ALL_USERS_URL+id+"/");
        return userString;
    }

    public static String getUserByName(String name){
        String userString = get(SHOW_ALL_USERS_URL+"name/"+name+"/");
        return userString;
    }

    public static String getAllContent() {
        String contentString = get(SHOW_ALL_CONTENTS_URL);
        return contentString;
    }

    public static String getContentByID(String id){
        String contentString = get(SHOW_ALL_CONTENTS_URL+id+"/");
        return contentString;
    }

    public static String getContentsFromUsername(String userName){
        String userString = DBGets.getUserByName(userName);
        UserBO actualUser = Parser.jsonUserToArray(userString)[0];
        return getContentsByUserID(String.valueOf(actualUser.getId()));
    }

    public static String getContentsByUserID(String userID){
        String contentStringJson = get(SHOW_ALL_CONTENTS_URL+"user/"+userID+"/");
        return contentStringJson;
    }

    public static String getAllServers(){
        String serversStringJson = get(SHOW_ALL_SERVERS_URL);
        return serversStringJson;
    }

    private static String get(String URL) {
        String resultString = "";
        try {
            HttpURLConnection conn = httpConnection(URL, "GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            resultString = br.readLine();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

}
