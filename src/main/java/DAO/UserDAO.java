package DAO;

import WebService.JDBC.PostgreSQLJDBC;

public class UserDAO {

    private static PostgreSQLJDBC postrgres = new PostgreSQLJDBC();

    public void insertNewUSer(String userName, String password){
        postrgres.insertNewUser(userName, password);
    }
}
