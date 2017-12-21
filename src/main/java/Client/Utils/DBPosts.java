package Client.Utils;

import Client.ClassesBO.UserBO;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBPosts {
    private static final String SIGNUPUSERURL = "http://localhost:8080/MyTube2.0Web/rest/user/new/";

    private static HttpURLConnection connectionCreator(String urlString) throws IOException {
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

    public static void registerUser(String userName, String password){
        try {
            HttpURLConnection conn = connectionCreator(SIGNUPUSERURL);

            Gson gson = new Gson();
            UserBO userBO = new UserBO();

            userBO.setUsername(userName);
            userBO.setPassword(password);

            String loginJson = gson.toJson(userBO);

            OutputStream os = conn.getOutputStream();
            os.write(loginJson.getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
