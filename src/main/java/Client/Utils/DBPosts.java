package Client.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBPosts {
    private static final String SIGNUPUSERURL = "http://4f67e3c6.ngrok.io/MyTube2.0Web/rest/user/new";

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

    static void registerUser(String userName, String password){
        try {
            HttpURLConnection conn = httpConnection(SIGNUPUSERURL);
            String loginJson = Parser.userToJsonRegister(userName, password);

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
