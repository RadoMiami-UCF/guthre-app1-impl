package baseline;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.ContentDisplay;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatePickerTableCell extends TableCell<ListItem, LocalDate> {
    private DatePicker datePicker;

    private final ObservableList<ListItem> observableTodoList;

    public DatePickerTableCell(ObservableList<ListItem> observableTodoList) {
        super();

        this.observableTodoList = observableTodoList;

        if (datePicker == null) {
            createDatePicker();
        }

        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (!isEditing()) {
                datePicker.setValue(item);
                if(item != null) {
                    setText(item.toString());
                } else {
                    setText("");
                }
            }
        }
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("YYYY-MM-DD");
        datePicker.setEditable(true);
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

        datePicker.setOnAction(t -> {
            LocalDate date = datePicker.getValue();

            setText(datePicker.getConverter().toString(date));
            commitEdit(date);

            if (null != observableTodoList) {
                observableTodoList.get(getIndex()).setDueDate(date);
                this.getTableView().refresh();
            }
        });

        datePicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue) {
                    datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
                    this.getTableView().refresh();
                }
            } catch (DateTimeParseException exception) {
                this.getTableView().refresh();
                var logger = Logger.getLogger("InvalidDateInputLogger");
                logger.log(Level.INFO, "Invalid string given for date!");
            }
        });

        setAlignment(Pos.CENTER);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
}
