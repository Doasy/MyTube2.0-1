package Server.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class DBGets extends DBGenerics{
    private static final String SHOW_ALL_CONTENTS_URL = GENERIC_URL + "content/";
    private static final String SHOW_ALL_SERVERS_URL = GENERIC_URL + "server/";


    public static String getAllContent() {
        String contentString = getAll(SHOW_ALL_CONTENTS_URL);
        return contentString;
    }

    public static String getAllServers(){
        String serversStringJson = getAll(SHOW_ALL_SERVERS_URL);
        return serversStringJson;
    }

    private static String getAll(String URL) {
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
