package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.model.Status;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sample.model.Constants.*;

public class StatusController {


    public TextField nameTextfield;
    public ListView<Status> listView;
    public Status selectedItem = null;
    PreparedStatement statement = null;
    AbstractDatabase conn = new MySQLConnector(user, dbPw, host, port, dbName);


    public void initialize() {
        listView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Status s = listView.getSelectionModel().getSelectedItem();
        if (s != null) {
            nameTextfield.setText(s.getName());
            selectedItem = s;
            System.out.println(s.getId());
        }
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (selectedItem == null) {
            newClicked(actionEvent);
        } else {
            try {
                statement = conn.getConnection().prepareStatement("UPDATE `gr7_status` SET `name` = '" + nameTextfield.getText() + "' WHERE `gr7_status`.`status_id` = '" + selectedItem.getId() + "'");
                statement.execute();
                listView.setItems(Status.getList());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        try {
            statement = conn.getConnection().prepareStatement("INSERT INTO gr7_status (name) VALUES ('" + nameTextfield.getText() + "')");
            statement.execute();
            listView.setItems(Status.getList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectedItem = null;
        nameTextfield.clear();
        listView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        try {
            statement = conn.getConnection().prepareStatement("DELETE from gr7_status WHERE name ='" + selectedItem.getName() + "'");
            statement.executeUpdate();
            listView.setItems(Status.getList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelClicked(ActionEvent actionEvent) {


    }
}
