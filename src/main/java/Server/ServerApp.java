package Server;

import Server.ServerInterfaceImpl.MyTubeImpl;
import Server.Utils.*;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerApp {
    private MyTubeImpl stub;
    private Registry registry;
    private final String host;
    private final int port;
    private final String registryName;
    private final String registryURL;


    private static void threadLauncher() throws UnknownHostException {
        Thread theThread = new Thread();
        theThread.start();
    }

    /**
     * Creates a ServerApp instance
     *
     * @param host the server waits for client connections on this IP
     * @param port port where the server listens for client petitions
     * @param registryName name of the registered service on RMI Registry
     */
    private ServerApp(String host, int port, String registryName) throws IOException {
        this.host = host;
        this.port = port;
        this.registryName = registryName;
        registryURL = "rmi://" + host + ":" + port
                + "/" + registryName;

        createDirectory();
    }

    private void createDirectory() throws IOException {
        Runtime.getRuntime().exec("mkdir ./server01");
    }


    public static void main(String args[]) throws IOException, NotBoundException {
        URL url = new URL("http://localhost:8080/MyTube2.0/user/new");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        String input = "{\"name\":\"nom\",\"password\":\"pass\"}";
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        String registryName = "MyTube";

        //Reads ServerApp Info
        String ownIP = Reader.ipServerReader();
        int ownPort = Reader.portServerReader();

        final ServerApp s = new ServerApp(ownIP, ownPort, registryName);

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void start() {
                System.out.println("Stopping server!");
                try {
                    mainThread.join();
                } catch (Exception e) {
                    System.out.println("Can not finalize main process");
                }
                try {
                    stopServer(s.getRegistry(), registryName, s.getStub());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        s.runServer();
        threadLauncher();
    }

    /**
     * Stopps the ServerApp
     */
    private static void stopServer(Registry registry, String registryName, MyTubeImpl stub) throws RemoteException {
        try{
            registry.unbind(registryName);
            UnicastRemoteObject.unexportObject(stub, true);
            System.out.println("ServerApp stopped correctly");
        } catch (Exception ex) {
            System.err.println("ServerApp failed on stop");
        }
    }

    /**
     * Runs the ServerApp
     */
    private void runServer() {
        try {
            System.setProperty("java.rmi.server.hostname", host);
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }


            stub = new MyTubeImpl();
            registry = Registrator.getRegistry(host, port);
            registry.rebind(registryName, stub);
            System.out.println("MyTube ServerApp ready on: " + registryURL);
        } catch (Exception ex) {
            System.err.println("ServerApp error: " + ex.toString());
        }
    }

    private Registry getRegistry(){
        return this.registry;
    }

    private MyTubeImpl getStub(){
        return this.stub;
    }
}
