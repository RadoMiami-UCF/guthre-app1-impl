/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package dialogboxes;

import baseline.ListItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddItemDialogBox {
    private ObservableList<ListItem> observableTodoList;

    private TableView<ListItem> listTable;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker datePicker;

    public void setUpDialogBox(List<ListItem> todoList, TableView<ListItem> listTable) {
        this.observableTodoList = (ObservableList<ListItem>) todoList;
        this.listTable = listTable;
    }

    @FXML
    public void initialize() {
        datePicker.setPromptText("YYYY-MM-DD");
        datePicker.setConverter(new StringConverter<>() {
            private static final String PATTERN = "yyyy-MM-dd";
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        datePicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue) {
                    datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
                }
            } catch (DateTimeParseException e) {
                var logger = Logger.getLogger("InvalidDateInputLogger");
                logger.log(Level.INFO, "Invalid string given for date!", e);
            }
        });
    }

    @FXML
    private void closeDialogBox() {
        //Simply close the dialog box.
        var stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createItem() {
        //First, check if the description is set.
        if(!"".equals(descriptionField.getCharacters().toString())) {
            //If it is, add a ListItem with the description and dueDate (if applicable) of the DatePicker to the list.
            //If I try to inline the variables within the ListItem instantiation, it breaks for no discernible reason.
            var descriptionString = descriptionField.getCharacters().toString();
            var dueDate = datePicker.getValue();
            observableTodoList.add(new ListItem(descriptionString, dueDate));
            //Then, refresh the listTable to make sure that it displays properly when the dialog box closes.
            listTable.refresh();
            //Then, close the dialog box.
            closeDialogBox();
        }
    }
}