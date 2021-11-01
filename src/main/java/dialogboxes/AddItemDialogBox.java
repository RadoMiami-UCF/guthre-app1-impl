/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package dialogboxes;

import baseline.TodoList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddItemDialogBox {
    private TodoList todoList;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createItemButton;

    @FXML
    private TextField descriptionOfNewItem;

    @FXML
    private DatePicker dueDateOfNewItem;

    @FXML
    private ChoiceBox<?> listChoiceBox;

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @FXML
    void closeDialogBox() {
        //Simply close the dialog box.
        var stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void createItem(MouseEvent event) {
        //First, check if the list, description, and due date are set.
        //If they are, call addItemToList(listIndex, description, dueDate).
    }
}