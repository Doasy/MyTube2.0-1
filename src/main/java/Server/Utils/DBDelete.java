package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.UserBO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class DBDelete extends DBGenerics {
    private static final String DELETE_CONTENT_URL = GENERIC_URL + "content/";

    public static String deleteContent(String id, String userName) {
        String response="";
        String contentString = DBGets.getContentByID(id);
        ContentBO contentBO = Parser.jsonContentToContent(contentString);
        String userString = DBGets.getUserByName(userName);
        UserBO actualUser = Parser.jsonUserToArray(userString)[0];
        if(contentBO.getUploader() == actualUser.getId()) {
            delete(DELETE_CONTENT_URL, String.valueOf(id));
        }else{
            response = "You can't delete this file. It isn't yours.";
        }
        return response;
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
