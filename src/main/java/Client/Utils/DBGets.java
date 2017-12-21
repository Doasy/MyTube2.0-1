package Client.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


class DBGets {
    private static final String SHOWALLUSERSURL = "http://localhost:8080/MyTube2.0Web/rest/user/";

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
