package Client.Utils;

import ClassesBO.UserBO;

class Validator {

    static boolean checkCredentials(String userName, String password, String usersRegistryJson){
        UserBO[] userBOS = Parser.jsonUsersToArray(usersRegistryJson);

        for(UserBO userBO: userBOS){
            if(userBO.getUsername().equals(userName) && userBO.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    static boolean nameIsUsed(String userName, String usersRegistryJson){
        UserBO[] userBOS = Parser.jsonUsersToArray(usersRegistryJson);

        for(UserBO userBO: userBOS){
            if(userBO.getUsername().equals(userName)){
                return true;
            }
        }
        return false;
    }
}
