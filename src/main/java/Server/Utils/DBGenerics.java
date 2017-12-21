package Server.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

class DBGenerics {
    protected static final String GENERIC_URL = "http://4f67e3c6.ngrok.io/MyTube2.0Web/rest/";

    protected static HttpURLConnection httpConnection(String urlString, String method) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method);

        conn.setRequestProperty("User-Agent", USER_AGENT);
        if(method.equals("POST") || method.equals("PUT")){
            conn.setRequestProperty("Content-Type", "application/json");
        }
        conn.setRequestProperty("Accept-Language", "UTF-8");

        return conn;
    }
}
