/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package baseline;

import dialogboxes.AddItemDialogBox;
import dialogboxes.DeleteItemDialogBox;
import dialogboxes.SaveDialogBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FXMLController {
    private static final String STYLES_CSS_URI_STRING = "styles.css";

    private TodoList todoList;

    @FXML
    private Menu addItemButton;

    @FXML
    private Menu deleteItemButton;

    @FXML
    private MenuItem loadButton;

    @FXML
    private MenuItem saveButton;

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @FXML
    void openAddItemDialogBox() throws IOException {
        /*Open a dialog box containing a drop-down of valid lists, and fields for a non-null description and due date,
        a button to create the item, and another to cancel the creation.*/
        var dialogStage = new Stage();
        //Make it so that the dialog box can't be resized.
        dialogStage.setResizable(false);
        //Make it so the main window is locked while this dialog box is open.
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        var loader = new FXMLLoader(Objects.requireNonNull(getClass()
                .getResource("AddItemDialogBox.fxml")));
        Parent root = loader.load();
        ((AddItemDialogBox) loader.getController()).setTodoList(todoList);

        var dialogScene = new Scene(root);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLES_CSS_URI_STRING))
                .toExternalForm());

        dialogStage.setTitle("Add An Item");
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    @FXML
    void openDeleteItemDialogBox() throws IOException {
        /*Open a dialog box containing two drop-downs: one to select the list the item is in, and another for the item
        to delete, in addition to two buttons: one to cancel the deletion, and another to confirm it.*/
        var dialogStage = new Stage();
        //Make it so that the dialog box can't be resized.
        dialogStage.setResizable(false);
        //Make it so the main window is locked while this dialog box is open.
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        var loader = new FXMLLoader(Objects.requireNonNull(getClass()
                .getResource("DeleteItemDialogBox.fxml")));
        Parent root = loader.load();
        ((DeleteItemDialogBox) loader.getController()).setTodoList(todoList);

        var dialogScene = new Scene(root);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLES_CSS_URI_STRING))
                .toExternalForm());

        dialogStage.setTitle("Delete An Item");
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    @FXML
    void openLoadDialogBox() {
        //First, open a fileChooser.
        //Then, call Application.todoListGroup.readListsFromFile(filePath).
    }

    @FXML
    void openSaveDialogBox() throws IOException {
        /*Open a dialog box containing a button to choose a file through a fileChooser, a list of all the lists in the
        form of a vbox with a checkbox next to each to indicate whether to include them in the save, and finally two
        lone buttons: one to cancel the save, and one to confirm it.*/
        var dialogStage = new Stage();
        //Make it so that the dialog box can't be resized.
        dialogStage.setResizable(false);
        //Make it so the main window is locked while this dialog box is open.
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        var loader = new FXMLLoader(Objects.requireNonNull(getClass()
                .getResource("SaveDialogBox.fxml")));
        Parent root = loader.load();
        ((SaveDialogBox) loader.getController()).setTodoList(todoList);

        var dialogScene = new Scene(root);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLES_CSS_URI_STRING))
                .toExternalForm());

        dialogStage.setTitle("Save Lists");
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }
}
