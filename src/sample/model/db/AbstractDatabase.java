package sample.model.db;

import java.sql.Connection;

public abstract class AbstractDatabase implements IDatabase {
    protected String host;
    protected int port;
    protected String dbName;
    protected String user;
    protected String pwd;

    protected static Connection conn = null;

    public AbstractDatabase(String user, String pwd, String host, int port, String dbName) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.pwd = pwd;
    }
}
