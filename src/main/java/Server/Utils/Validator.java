package Server.Utils;

import Server.ClassesBO.ServerBO;
public class Validator {

    public static int checkServerCredentials(String ip, String port, String serversRegistryJson){
        ServerBO[] serverBOS = Server.Utils.Parser.jsonServerToArray(serversRegistryJson);

        for(ServerBO serverBO: serverBOS){
            if(serverBO.getHost().equals(ip) && Integer.toString(serverBO.getPort()).equals(port)){
                return serverBO.getId();
            }
        }

        return -1;
    }
}
