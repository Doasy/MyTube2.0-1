package Client.ClassesBO;

import java.io.Serializable;

public class ServerBO implements Serializable{

    int port;
    String host;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
