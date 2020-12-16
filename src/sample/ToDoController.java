package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.model.Priority;
import sample.model.Status;
import sample.model.ToDo;

public class ToDoController {
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ComboBox statusComboBox;
    public ComboBox priorityComboBox;
    public AnchorPane toDoPane;
    private ToDo selected = null;

    public void setToDo(ToDo item) {
        selected = item;
        displayItem();
    }

    private void displayItem() {
        // Hier sollten die Daten von "Selected" dargestellt werden.
        int i = 0;

        statusComboBox.setItems(new Status().getList());
        priorityComboBox.setItems(new Priority().getList());

        nameTextField.setText(selected.getName());
        descriptionTextArea.setText(selected.getDescription());

        for (i = 0; i < statusComboBox.getItems().size(); i++) {
            if (((Status) statusComboBox.getItems().get(i)).getId() == selected.getStatusId()) {
                break;
            }

            statusComboBox.getSelectionModel().select(i);
        }
        for (i = 0; i < priorityComboBox.getItems().size(); i++) {
            if (((Priority) priorityComboBox.getItems().get(i)).getId() == selected.getPriorityId()) {
                break;
            }

            priorityComboBox.getSelectionModel().select(i);
        }
    }
}
