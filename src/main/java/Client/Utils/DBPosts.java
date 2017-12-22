package Client.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBPosts extends DBGenerics{
    private static final String SIGNUPUSERURL = GENERIC_URL + "user/new/";

    static void registerUser(String userName, String password){
        try {
            HttpURLConnection conn = httpConnection(SIGNUPUSERURL, "POST");
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
