package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.Status;
import sample.model.db.AbstractDatabase;
import sample.model.db.MySQLConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusController {


    public TextField nameTextfield;
    public ListView<Status> statusListView;
    public Status selectedItem = null;
    PreparedStatement statement = null;
    AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at",3306,"d0345761");




    public void initialize(){
        statusListView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();
        if(s != null){
            nameTextfield.setText(s.getName());
            selectedItem = s;
            System.out.println(s.getId());
        }
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem == null){
            newClicked(actionEvent);
        }else {
            try {
                statement = conn.getConnection().prepareStatement("UPDATE `gr2_status` SET `name` = '"+nameTextfield.getText()+"' WHERE `gr2_status`.`status_id` = '"+selectedItem.getId()+"'");
                statement.execute();
                statusListView.setItems(Status.getList());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        try {
            statement = conn.getConnection().prepareStatement("INSERT INTO gr2_status (name) VALUES ('" + nameTextfield.getText()+"')");
            statement.execute();
            statusListView.setItems(Status.getList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectedItem = null;
        nameTextfield.clear();
        statusListView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        try {
            statement = conn.getConnection().prepareStatement("DELETE from gr2_status WHERE name ='" + selectedItem.getName() +"'");
            statement.executeUpdate();
            statusListView.setItems(Status.getList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage =Controller.statusStage;
        stage.close();
    }
}
