package Server.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class DBGets extends DBGenerics{
    private static final String SHOWALLCONTENTURL = GENERICURL + "content/";
    private static final String SHOWALLSERVERSURL = GENERICURL + "server/";

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
        String contentString = getAll(SHOWALLCONTENTURL);
        return contentString;
    }

    public static String getAllServers(){
        String serversStringJson = getAll(SHOWALLSERVERSURL);
        return serversStringJson;
    }

    private static String getAll(String URL) {
        String resultString = "";
        try {
            HttpURLConnection conn = httpConnection(URL);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            resultString = br.readLine();

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

}
