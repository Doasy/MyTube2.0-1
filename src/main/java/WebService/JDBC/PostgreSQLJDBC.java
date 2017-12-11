package WebService.JDBC;


import WebService.BO.UserBO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
    Connection c = null;

    public void openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mytube2",
                            "postgres", "1234");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("problem opening the connection");
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void closeConnection(){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("problem closing the connection");
        }
        System.out.println("Closed database successfully");
    }

    public int insertNewUser(UserBO user){
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO user (username, password) "
                    + "VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"');";
            stmt.executeUpdate(sql);
            return 1;
        } catch (SQLException e) {
            System.err.println("problem executing the query");
            return -1;
        }
    }
}
