package Server.Utils;

import Client.Utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBPosts {
    private static final String SIGNUPSERVERURL = "http://localhost:8080/MyTube2.0Web/rest/server/new";

    private static HttpURLConnection httpConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");

        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept-Language", "UTF-8");

        return conn;
    }

    public static void registerServer(String ip, String port){
        try {
            HttpURLConnection conn = httpConnection(SIGNUPSERVERURL);
            String loginJson = Client.Utils.Parser.userToJsonRegister(userName, password);

            OutputStream os = conn.getOutputStream();
            os.write(loginJson.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            in.close();

        } catch (IOException ignore) {}
    }

}
