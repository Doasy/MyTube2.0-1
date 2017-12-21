package Server.ServerRemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyTubeInterface extends Remote {

    List<String> searchFromKeyword(String keyword) throws RemoteException;

    String searchAll() throws RemoteException;

    List<String> showOwnFiles(String username) throws RemoteException;

    String uploadContent(String title, String description, byte[] fileData, String userName) throws RemoteException;

    String modifyContent(String id, String title, String description) throws RemoteException;

    String deleteContent(String id, String userName) throws RemoteException;

    byte[] downloadContent(int id) throws RemoteException;

    byte[] getLocalContent(int id, String name) throws RemoteException;

    void addCallback(MyTubeCallbackInterface callbackObject) throws RemoteException;

    void removeCallback(MyTubeCallbackInterface callbackObject) throws RemoteException;

    boolean isValidID(int ID) throws RemoteException;
}
