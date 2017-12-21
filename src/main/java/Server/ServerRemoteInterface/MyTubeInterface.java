package Server.ServerRemoteInterface;

import ClassesBO.ContentBO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyTubeInterface extends Remote {

    List<ContentBO> searchFromKeyword(String keyword) throws RemoteException;

    String searchAll() throws RemoteException;

    List<String> showOwnFiles(String username) throws RemoteException;

    String uploadContent(byte[] content, String ip, int port,  String userId, String title, String description) throws RemoteException;

    String modifyContent(String id, String title, String description, String username) throws RemoteException;

    String deleteContent(String id, String userName) throws RemoteException;

    byte[] downloadContent(int id) throws RemoteException;

    byte[] getLocalContent(int id, String name) throws RemoteException;

    void addCallback(MyTubeCallbackInterface callbackObject) throws RemoteException;

    void removeCallback(MyTubeCallbackInterface callbackObject) throws RemoteException;

    boolean isValidID(int ID) throws RemoteException;
}
