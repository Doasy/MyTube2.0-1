package Server.ClassesBO;

import java.io.Serializable;

public class ServerBO implements Serializable{

    int id;
    int port;
    String host;

    public int getId(){ return id; }

    public void setId(int id){ this.id = id; }

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
