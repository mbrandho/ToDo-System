package sample.model.db;

import java.sql.Connection;

public interface IDatabase {

    Connection getConnection();

}
