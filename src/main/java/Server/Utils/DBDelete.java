package Server.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class DBDelete extends DBGenerics {
    private static final String DELETE_CONTENT_URL = GENERIC_URL + "content/";

    public static void deleteContent(int id) {
        delete(DELETE_CONTENT_URL, String.valueOf(id));
    }

    private static void delete(String URL, String id) {
        String resultString = "";
        try {
            HttpURLConnection conn = httpConnection(URL + id + "/", "DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            resultString = br.readLine();

            if (conn.getResponseCode() != 200) {
                System.err.println("Something fails deleting");
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
