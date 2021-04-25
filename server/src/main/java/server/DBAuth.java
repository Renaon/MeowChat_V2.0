package server;

import java.sql.*;

public class DBAuth implements AuthService{
    private static Connection connection;
    private static Statement statement;

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:meowchat.db");
        statement = connection.createStatement();
    }

    private void disconnect() {
        try {
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        String sqllogin = "";
        try{
            connect();
            ResultSet res = statement.executeQuery("SELECT LOGIN FROM c_users WHERE LOGIN = '" + login +
                    "' AND PASSWORD = '" + password + "';");
            sqllogin = res.getString(1);

            res.close();


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return sqllogin;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        int res = 0;
        try{
            connect();
            if(getNicknameByLoginAndPassword(login, password).isEmpty()) {
                res = statement.executeUpdate("INSERT INTO c_users (LOGIN, PASSWORD)  VALUES('" +
                        login + "', '" + password + "');");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res > 0;
    }
}
