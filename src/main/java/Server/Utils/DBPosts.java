package Server.Utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


public class DBPosts extends DBGenerics{
    private static final String SIGN_UP_SERVER_URL = GENERIC_URL +"server/new";
    private static final String UPLOAD_SERVER_URL = GENERIC_URL + "content/new";
    public static void registerServer(String ip, String port){
        try {
            HttpURLConnection conn = httpConnection(SIGN_UP_SERVER_URL, "POST");
            String loginJson = Server.Utils.Parser.serverToJsonRegister(ip, port);

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

    public static void uploadServer(String serverId, String userId, String title, String description){
        try{
            HttpURLConnection conn = httpConnection(UPLOAD_SERVER_URL, "POST");
            String registerJson = Server.Utils.Parser.contentToJsonRegister(serverId, userId, title, description);

            OutputStream os = conn.getOutputStream();
            os.write(registerJson.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            in.close();
        }catch(IOException ignore){}
    }

}
