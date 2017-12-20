package Server.ServerInterfaceImpl;

import Server.ServerRemoteInterface.MyTubeCallbackInterface;
import Server.ServerRemoteInterface.MyTubeInterface;
import com.google.gson.Gson;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;


public class MyTubeImpl extends UnicastRemoteObject implements MyTubeInterface {
    private static final String  SHOWALLURL = "http://localhost:8080/MyTube2.0Web/rest/content/";
    private static final String  UPLOADCONTENTURL = "http://0bca118c.ngrok.io/MyTube2.0Web/rest/content";
    private Set<MyTubeCallbackInterface> callbackObjects;

    public MyTubeImpl() throws IOException, ParserConfigurationException {
        super();
        callbackObjects = new HashSet<>();
    }


    @Override
    public boolean isValidID(int ID) throws RemoteException{
        //TODO
        return false;
    }

    @Override
    public List<String> searchFromKeyword(String keyword) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public List<String> showOwnFiles(String username) throws RemoteException {
        return null;
    }

    @Override
    public List<String> searchAll() throws RemoteException {
        URL url;
        String inputLine;
        List<String> contentList = new ArrayList<>();

        try {
            url = new URL("http://0bca118c.ngrok.io/MyTube2.0Web/rest/content/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((inputLine = br.readLine()) != null){
                contentList.add(inputLine);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentList;
    }

    @Override
    public synchronized String uploadContent(String title, String description, byte[] fileData, String userName) throws RemoteException {
        URL url = null;
        try {
            url = new URL(UPLOADCONTENTURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", USER_AGENT);

            String input = "{\"username\":\"ngrok\",\"password\":\"iPad 4\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyContent(String id, String title, String description) throws RemoteException{
        //TODO
        return null;
    }

    @Override
    public String deleteContent(String id, String userName) throws RemoteException {

        return null;
    }

    @Override
    public byte[] downloadContent(int id) throws RemoteException {
        String ip = "";
        int port = 0;
        byte[] content = null;
        String name = "";
        URL url;

        try {
            if(Inet4Address.getLocalHost().getHostAddress().equals(ip)){
                content = getLocalContent(id, name);
            }else{
                MyTubeInterface stub = connectToTheServer(ip, port);
                content = stub.getLocalContent(id, name);
            }
        } catch (UnknownHostException | NotBoundException e) {
            e.printStackTrace();
        }

        return content;
    }

    @Override
    public void addCallback(MyTubeCallbackInterface callbackObject)
            throws RemoteException {
        callbackObjects.add(callbackObject);
        System.out.println("New client registered on callback list. (" + callbackObjects.size() + " clients)");
    }

    @Override
    public void removeCallback(MyTubeCallbackInterface callbackObject) throws RemoteException {
        callbackObjects.remove(callbackObject);
        System.out.println("A client have been removed "
                + "from the callback list. (" + callbackObjects.size() + " clients)");
    }


    @Override
    public byte[] getLocalContent(int id, String name) throws RemoteException{
        String path = "./server01/" + Integer.toString(id) + "/" + name;
        byte[] content = null;
        try {
            content = Server.Utils.FileDissasembler.fileDissasembler(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void notifyAllNewContent(String title) throws RemoteException {
        for (MyTubeCallbackInterface callback : callbackObjects) {
            callback.notifyNewContent(title);
        }
    }

    private MyTubeInterface connectToTheServer(String ip, int port) throws NotBoundException {
        MyTubeInterface stub = null;
        try {
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Registry registry = LocateRegistry.getRegistry(ip, port);
            stub = (MyTubeInterface) registry.lookup("MyTube");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return stub;
    }
}
