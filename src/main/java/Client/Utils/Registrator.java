package Client.Utils;


public class Registrator {

    public static String signIn(String pass){
        return "thing";
    }

    public static String singUp(String pass){
        String usersRegistryJson = DBGets.getAllUsers();
        String userName = Reader.nickNameReader();
        boolean nameInUse = Validator.nameIsUsed(userName, usersRegistryJson);

        while(nameInUse){
            userName = Reader.userNameInUse();
            nameInUse = Validator.nameIsUsed(userName, usersRegistryJson);
        }

        DBPosts.registerUser(userName, pass);
        return userName;
    }
}
