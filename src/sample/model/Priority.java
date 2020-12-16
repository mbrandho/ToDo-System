package sample.model;
import static sample.model.Constants.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Priority {
    private int id;
    private String name;

    public Priority(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Priority() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> getList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector(user, dbPw, host, port, dbName);

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr7_priority");
            ResultSet results = statement.executeQuery();

            while(results.next()) {
                Priority tmp = new Priority(results.getInt("priority_id"), results.getString("NAME"));

                list.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
