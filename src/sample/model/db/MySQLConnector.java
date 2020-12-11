package sample.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector extends AbstractDatabase {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MySQLConnector(String user, String pwd, String host, int port, String dbName) {
        super(user, pwd, host, port, dbName);
    }

    @Override
    public Connection getConnection() {
        if (conn == null) {
            /**
             * Verbindungsaufbau zur Datenbank
             */
            try {
                // jdbc:mysql://xserv:5432/pagila
                conn = DriverManager.
                        getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false",
                                user,
                                pwd);
            } catch (SQLException throwables) {
            }
        }

        return conn;
    }

}
