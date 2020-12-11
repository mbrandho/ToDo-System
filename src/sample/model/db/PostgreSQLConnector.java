package sample.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnector extends AbstractDatabase {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PostgreSQLConnector(String user, String pwd, String host, int port, String dbName) {
        super(user, pwd, host, port, dbName);
    }

    @Override
    public Connection getConnection() {
        if (conn == null) {
            /**
             * Verbindungsaufbau zur Datenbank
             */
            try {
                // jdbc:posgresql://xserv:5432/pagila
                conn = DriverManager.
                        getConnection("jdbc:postgresql://" + host + ":" + port + "/" + dbName,
                                user,
                                pwd);
            } catch (SQLException throwables) {
            }
        }

        return conn;
    }

}
