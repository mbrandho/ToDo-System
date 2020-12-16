package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private int zip;
    private String street;
    private String name;
    private String city;

    public User(int id, int zip, String street, String name, String city){
        this.id = id;
        this.zip = zip;
        this.street = street;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
    public static ObservableList<User> getList()  {
        ObservableList<User>list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector(Constants.user, Constants.dbPw, Constants.host,Constants.port,Constants.dbName);

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("SELECT * from gr7_editor");
            ResultSet results = statement.executeQuery();

            while (results.next()){
                User tmp = new User(results.getInt("editor_id"),results.getInt("zip"),results.getString("street"),results.getString("name"),results.getString("city"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
