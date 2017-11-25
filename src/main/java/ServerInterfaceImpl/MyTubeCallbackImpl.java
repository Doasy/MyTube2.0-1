package main.java.ServerInterfaceImpl;

import main.java.ServerRemoteInterface.MyTubeCallbackInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyTubeCallbackImpl extends UnicastRemoteObject implements MyTubeCallbackInterface {

    public MyTubeCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notifyNewContent(String title) throws RemoteException{
        System.out.println("New content has been added title "+title);
    }

    @Override
    public void notifyServerStopped() throws RemoteException {
        System.out.print("Server stopped");
        System.exit(0);
    }
}
