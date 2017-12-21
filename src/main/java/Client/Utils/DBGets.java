package Client.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBGets {
    private static final String SHOWALLUSERSURL = "http://4f67e3c6.ngrok.io/MyTube2.0Web/rest/user/";
    private static final String GET_USER = "http://4f67e3c6.ngrok.io/MyTube2.0Web/rest/user/name/";

    public static String getUser(String userName){
        URL url;
        String inputLine = "";

        try {
            url = new URL(GET_USER+userName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Accept-Language", "UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            inputLine = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }

    static String getAllUsers(){
        URL url;
        String inputLine = "";

        try {
            url = new URL(SHOWALLUSERSURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Accept-Language", "UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            inputLine = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }
}
