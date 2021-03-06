package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    private int id;
    private String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status() {

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

    public static ObservableList<Status> getList() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector(Constants.user, Constants.dbPw, Constants.host, Constants.port, Constants.dbName);

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("SELECT * from gr7_status");


            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Status tmp = new Status(results.getInt("status_id"), results.getString("name"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
