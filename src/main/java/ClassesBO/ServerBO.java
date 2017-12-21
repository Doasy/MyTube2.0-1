package ClassesBO;

import java.io.Serializable;

public class ServerBO implements Serializable{

    int id;
    int port;
    String host;
    boolean on;

    public boolean getOn(){ return on;}

    public void setOn(boolean on){ this.on = on;}

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
