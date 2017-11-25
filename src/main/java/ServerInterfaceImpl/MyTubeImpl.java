package main.java.ServerInterfaceImpl;

import main.java.Server.Server;
import main.java.ServerRemoteInterface.MyTubeCallbackInterface;
import main.java.ServerRemoteInterface.MyTubeInterface;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * AQUI ANAE LA BASE DE DADES XML QUE PASSARA A SER UNA SQL.
 */
public class MyTubeImpl extends UnicastRemoteObject implements MyTubeInterface {
    private Set<MyTubeCallbackInterface> callbackObjects;

    public MyTubeImpl() throws IOException, ParserConfigurationException {
        super();
        callbackObjects = new HashSet<>();
    }

    @Override
    public String getTitleFromKey(int key) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public String getContentFromKey(int key) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public boolean isValidID(int ID) throws RemoteException{
        //TODO
        return false;
    }

    @Override
    public String getContentFromTitle(String title) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public List<String> searchFromKeyword(String keyword) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public List<String> searchAll() throws RemoteException {
        //TODO
        return null;
    }


    @Override
    public synchronized String uploadContent(String title, String description, byte[] fileData, String userName) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public String modifyContent(String id, String title, String description) throws RemoteException{
        //TODO
        return null;
    }

    @Override
    public String deleteContent(String id, String userName) throws RemoteException {
        //TODO
        return null;
    }

    @Override
    public byte[] downloadContent(int id) throws RemoteException {
        //TODO
        return null;
    }

    public List<String> showOwnFiles(String userName) throws RemoteException {
        //TODO
        return null;
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

    private void notifyAllNewContent(String title) throws RemoteException {
        for (MyTubeCallbackInterface callback : callbackObjects) {

            callback.notifyNewContent(title);
        }
    }
}
