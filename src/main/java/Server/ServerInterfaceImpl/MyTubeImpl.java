package Server.ServerInterfaceImpl;

import ClassesBO.ContentBO;
import ClassesBO.ServerBO;
import Server.ServerRemoteInterface.MyTubeCallbackInterface;
import Server.ServerRemoteInterface.MyTubeInterface;
import Server.Utils.*;


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
import java.util.concurrent.TimeUnit;


public class MyTubeImpl extends UnicastRemoteObject implements MyTubeInterface {
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
    public List<ContentBO> searchFromKeyword(String keyword) throws RemoteException {
        String allContent = DBGets.getAllContent();
        ArrayList<ContentBO> desiredContent = Validator.selectWantedContent(allContent, keyword);
        return desiredContent;
    }

    @Override
    public List<String> showOwnFiles(String username) throws RemoteException {
        String contentsJson = DBGets.getContentsFromUsername(username);
        ContentBO[]  contents = Parser.jsonContentToArray(contentsJson);
        List<String> contentsList = new ArrayList<>();
        if(contents != null && contents.length!=0) {
            for (ContentBO content : contents) {
                String contentString = "ID: " + content.getId() + " Title: " + content.getTitle() + " Description: " + content.getDescription();
                contentsList.add(contentString);
            }
        }
        return contentsList;
    }

    @Override
    public String searchAll() throws RemoteException {
        return DBGets.getAllContent();
    }

    @Override
    public synchronized String uploadContent(byte[] content, String ip, int port,  String userId, String title, String description) throws RemoteException {
        String serversList = DBGets.getAllServers();
        int idServer = Validator.checkServerCredentials(ip, Integer.toString(port), serversList);
        DBPosts.uploadServer(Integer.toString(idServer), userId, title, description);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String allContent = DBGets.getAllContent();
        int idContent = Validator.selectSpecificContent(allContent, title, Integer.parseInt(userId), idServer);
        String pathOfFile = "./server01/" + Integer.toString(idContent) + "/";
        try {
            Runtime.getRuntime().exec("mkdir ./server01/" + Integer.toString(idContent));
            FileAssembler.fileAssembler(pathOfFile, content, title);
            return "File Uploaded Succesfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error Uploading File";
        }
    }

    @Override
    public String modifyContent(String id, String title, String description, String userName) throws RemoteException{

        String StringContent = DBGets.getContentByID(id);
        ContentBO contentBO = Parser.jsonContentToContent(StringContent);
        String StringServer = DBGets.getAllServers();
        ServerBO serverBO = Validator.wantedServer(StringServer, contentBO.getServerId());


        MyTubeInterface stub = null;
        try {
            stub = connectToTheServer(serverBO.getHost(), serverBO.getPort());
            stub.modifyLocalContent(id, title, contentBO.getTitle());
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return DBPuts.modifyContent(id, title, description, userName);
    }

    @Override
    public void modifyLocalContent(String id, String newName, String OldName) throws RemoteException{
        try {
            Runtime.getRuntime().exec("mv ./server01/" + id + "/" + OldName + " " + "./server01/" + id + "/" + newName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String deleteContent(String id, String userName) throws RemoteException {

        String StringContent = DBGets.getContentByID(id);
        ContentBO contentBO = Parser.jsonContentToContent(StringContent);
        String StringServer = DBGets.getAllServers();
        ServerBO serverBO = Validator.wantedServer(StringServer, contentBO.getServerId());


        MyTubeInterface stub = null;
        try {
            stub = connectToTheServer(serverBO.getHost(), serverBO.getPort());
            stub.deleteLocalContent(id);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        return DBDelete.deleteContent(id, userName);
    }

    @Override
    public void deleteLocalContent(String id){
        try {
            Runtime.getRuntime().exec("rm -r ./server01/" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] downloadContent(int id) throws RemoteException {
        byte[] content = null;

        String StringContent = DBGets.getContentByID(Integer.toString(id));
        ContentBO contentBO = Parser.jsonContentToContent(StringContent);
        String StringServer = DBGets.getAllServers();
        ServerBO serverBO = Validator.wantedServer(StringServer, contentBO.getServerId());


        MyTubeInterface stub = null;
        try {
            stub = connectToTheServer(serverBO.getHost(), serverBO.getPort());
            content = stub.getLocalContent(id, contentBO.getTitle());
        } catch (NotBoundException e) {
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
