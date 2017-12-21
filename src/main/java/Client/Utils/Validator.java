package Client.Utils;

import Client.ClassesBO.UserBO;
import com.google.gson.Gson;


class Validator {

    static boolean nameIsUsed(String userName, String usersRegistryJson){
        Gson gson = new Gson();

        UserBO[] userBOS = gson.fromJson(usersRegistryJson, UserBO[].class);
        for(UserBO userBO: userBOS){
            if(userBO.getUsername().equals(userName)){
                return true;
            }
        }

        return false;
    }
}
