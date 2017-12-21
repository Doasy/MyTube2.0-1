package Server.Utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


public class DBPosts extends DBGenerics{
    private static final String SIGN_UP_SERVER_URL = GENERIC_URL +"server/new";


    public static void registerServer(String ip, String port){
        try {
            HttpURLConnection conn = httpConnection(SIGN_UP_SERVER_URL, "POST");
            String loginJson = Server.Utils.Parser.serverToJsonRegister(ip, port);

            OutputStream os = conn.getOutputStream();
            os.write(loginJson.getBytes());
            os.flush();
            os.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            in.close();

        } catch (IOException ignore) {}
    }

}
