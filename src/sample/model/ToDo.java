package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {
    private int todoId = 0;
    private String name = "";
    private String description = "";
    private int statusId = 0;
    private int priorityId = 0;

    public ToDo(int todoId, String name, String description, int statusId, int priorityId) {
        this.todoId = todoId;
        this.name = name;
        this.description = description;
        this.statusId = statusId;
        this.priorityId = priorityId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<ToDo> getList() {
        ObservableList<ToDo> list = FXCollections.observableArrayList();
        AbstractDatabase conn = new MySQLConnector(Constants.user, Constants.dbPw, Constants.host, Constants.port, Constants.dbName);

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr7_todo");
            ResultSet results = statement.executeQuery();

            while(results.next()) {
                ToDo tmp = new ToDo(results.getInt("todo_id"), results.getString("name"), results.getString("description"),
                        results.getInt("status_id"), results.getInt("priority_id"));

                list.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
