package Server.Utils;

import ClassesBO.ContentBO;
import ClassesBO.UserBO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class DBPuts extends DBGenerics{
    private static final String UPDATE_CONTENT_URL = GENERIC_URL + "content/";


    public static String modifyContent(String id, String title, String description, String userName) {
        String response="";
        String contentString = DBGets.getContentByID(id);
        ContentBO contentBO = Parser.jsonContentToContent(contentString);
        String userString = DBGets.getUserByName(userName);
        UserBO actualUser = Parser.jsonUserToArray(userString)[0];
        if(contentBO.getUploader() == actualUser.getId()) {
            modify(UPDATE_CONTENT_URL, String.valueOf(id), title, description);
        }else{
            response = "You can't delete this file. It isn't yours.";
        }
        return response;
    }

    private static void modify(String URL, String id, String title, String description) {
        try {
            HttpURLConnection conn = httpConnection(UPDATE_CONTENT_URL+id+"/", "PUT");
            String loginJson = Server.Utils.Parser.contentToJsonRegister(title, description);

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
