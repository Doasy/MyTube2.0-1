package Client.Utils;


public class Registrator {

    public static String signIn(){
        String usersRegistryJson = DBGets.getAllUsers();
        String userName = Reader.usernameReader();
        String password = Reader.userPasswordReader();
        boolean creadentials = Validator.checkCredentials(userName, password, usersRegistryJson);

        while(!creadentials){
            userName = Reader.usernameReader();
            password = Reader.userPasswordReader();
            creadentials = Validator.checkCredentials(userName, password, usersRegistryJson);
        }

        return userName;
    }

    public static String singUp(){
        String usersRegistryJson = DBGets.getAllUsers();
        String userName = Reader.usernameReader();
        String pass = Reader.userPasswordReader();
        boolean nameInUse = Validator.nameIsUsed(userName, usersRegistryJson);

        while(nameInUse){
            userName = Reader.userNameInUse();
            nameInUse = Validator.nameIsUsed(userName, usersRegistryJson);
        }

        DBPosts.registerUser(userName, pass);
        return userName;
    }
}
