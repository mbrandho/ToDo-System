package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void onStatusClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage stage = new Stage();

            root = FXMLLoader.load(getClass().getResource("StatusGUI.fxml"));
            stage.setTitle("Stati");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPriorityClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage stage = new Stage();

            root = FXMLLoader.load(getClass().getResource("priority.fxml"));
            stage.setTitle("Prioritäten");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
