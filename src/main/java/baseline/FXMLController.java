/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package baseline;

import dialogboxes.AddItemDialogBox;
import dialogboxes.DeleteItemDialogBox;
import dialogboxes.SaveDialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLController {
    private static final String STYLES_CSS_URI_STRING = "styles.css";

    private final ObservableList<ListItem> observableTodoList = FXCollections.observableArrayList(new ArrayList<>());

    @FXML
    private TableColumn<ListItem, String> descriptionCol;

    @FXML
    private TableColumn<ListItem, LocalDate> dueDateCol;

    @FXML
    private TableView<ListItem> listTable;

    @FXML
    public void initialize() {
        //The table is set to be editable.
        listTable.setEditable(true);
        /*The description column is set to use the default cell factory for TextFieldTableCells and the property factory
        of "description".*/
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        //When the description gets edited...
        descriptionCol.setOnEditCommit(
                (TableColumn.CellEditEvent<ListItem, String> t) -> {
                    if(!"".equals(t.getNewValue())) {
                        //If the description wasn't zeroed out, update the item with the new description.
                        (t.getTableView().getItems().get(t.getTablePosition().getRow()))
                                .setDescription(t.getNewValue());
                    }
                    //Then, refresh the table to make sure it stays consistent with the array list.
                    listTable.refresh();
                });

        //The due date column is set to use the property factory of "dueDate".
        dueDateCol.setCellFactory(p -> new DatePickerTableCell(observableTodoList));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        //Finally, make the list table show the items in observableTodoList.
        listTable.setItems(observableTodoList);
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
        ((AddItemDialogBox) loader.getController()).setUpDialogBox(observableTodoList, listTable);

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
        ((DeleteItemDialogBox) loader.getController()).setUpDialogBox(observableTodoList, listTable);

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
        var chooser = new FileChooser();
        chooser.setTitle("Load from...");
        //Then, write the output of the fileChooser to a file.
        var file = chooser.showOpenDialog(new Stage());
        //Then, if the file exists, try to read from the file.
        if (file != null && file.exists()) {
            try(var f = new FileInputStream(file); var o = new ObjectInputStream(f)) {
                //Then, call overwriteListTable.
                overwriteListTable(o);
                //Finally, refresh the listTable so that it's consistent with what was loaded.
                listTable.refresh();
            } catch (FileNotFoundException e) {
                var logger = Logger.getLogger("FileNotFoundLogger");
                logger.log(Level.WARNING, "File could not be opened, or is a directory!", e);

            } catch (IOException e) {
                var logger = Logger.getLogger("IOExceptionLogger");
                logger.log(Level.SEVERE, "Unforeseen IO error!", e);

            } catch (ClassNotFoundException e) {
                var logger = Logger.getLogger("ClassNotFoundLogger");
                logger.log(Level.SEVERE, "Class could not be found! Maybe the wrong file was opened?", e);

            } catch (ClassCastException e) {
                var logger = Logger.getLogger("ClassCastLogger");
                logger.log(Level.SEVERE, "Incorrect class in file: ", e);
            }
        }
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
        ((SaveDialogBox) loader.getController()).setTodoList(observableTodoList);

        var dialogScene = new Scene(root);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLES_CSS_URI_STRING))
                .toExternalForm());

        dialogStage.setTitle("Save Lists");
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void overwriteListTable(ObjectInputStream o) throws IOException, ClassNotFoundException, ClassCastException{
        var inPossiblyList = o.readObject();
        List<ListItem> result = new ArrayList<>();
        if (inPossiblyList instanceof List<?> listOfMaybeListItem) {
            for(Object item : listOfMaybeListItem){
                if(item instanceof ListItem listItem) {
                    result.add(listItem);
                } else {
                    throw new ClassCastException("Class read was a list, but not of ListItems!");
                }
            }
        } else {
            throw new ClassCastException("Class read was not a list!");
        }
        observableTodoList.setAll(result);
    }
}
