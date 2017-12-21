package Server.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBGets {
    private static final String SHOWALLCONTENTURL = "http://localhost:8080/MyTube2.0Web/rest/content/";
    private static final String SHOWALLSERVERSURL = "http://localhost:8080/MyTube2.0Web/rest/server/";

    private static HttpURLConnection httpConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept-Language", "UTF-8");

        return conn;
    }

    public static String getAllContent() {
        String contentString = "";

        try {
            HttpURLConnection conn = httpConnection(SHOWALLCONTENTURL);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            contentString = br.readLine();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentString;
    }

    public static String getAllServers(){
        String serversStringJson = "";
        try {
            HttpURLConnection conn = httpConnection(SHOWALLSERVERSURL);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            serversStringJson = br.readLine();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return serversStringJson;
    }

}
