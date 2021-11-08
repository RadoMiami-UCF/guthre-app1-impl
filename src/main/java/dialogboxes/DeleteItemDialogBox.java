/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package dialogboxes;

import baseline.ListItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class DeleteItemDialogBox {
    private ObservableList<ListItem> observableTodoList;

    private TableView<ListItem> listTable;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<ListItem> itemChoiceBox;

    public void setUpDialogBox(List<ListItem> todoList, TableView<ListItem> listTable) {
        this.observableTodoList = (ObservableList<ListItem>) todoList;
        itemChoiceBox.getItems().addAll(observableTodoList);
        this.listTable = listTable;
    }

    @FXML
    private void closeDialogBox() {
        //Simply close the dialog box.
        var stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void removeItem() {
        //First, check to see if an item has been chosen.
        if(itemChoiceBox.getSelectionModel().getSelectedItem() != null) {
            //If that's the case, call observableTodoList.deleteItemFromList(itemIndex).
            observableTodoList.remove(itemChoiceBox.getSelectionModel().getSelectedIndex());
            //Then, refresh the listTable to make sure that it displays properly when the dialog box closes.
            listTable.refresh();
            //Then, close the dialog box.
            closeDialogBox();
        }
    }
}

