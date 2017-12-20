package WebService.JDBC;


import WebService.BO.UserBO;

import java.sql.*;

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
            String sql = "INSERT INTO myTube_user (username, password) "
                    + "VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"');";
            stmt.executeUpdate(sql);
            return 1;
        } catch (SQLException e) {
            System.err.println("problem executing the query");
            return -1;
        }
    }

    public UserBO getUserById(int id){
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "SELECT * FROM myTube_user WHERE id = "+id+";";
            ResultSet rs =stmt.executeQuery(sql);
            UserBO userBO = new UserBO();
            while (rs.next()) {
                userBO.setId(Integer.parseInt(rs.getString("id")));
                userBO.setUsername(rs.getString("username"));
                userBO.setPassword(rs.getString("password"));
            }
            return userBO;

        } catch (SQLException e) {
            System.err.println("problem executing the query");
            return null;
        }
    }
}
