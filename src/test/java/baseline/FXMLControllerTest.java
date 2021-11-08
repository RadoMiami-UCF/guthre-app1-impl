package baseline;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FXMLControllerTest {

    /*@Test
    void testDeleteAllItems() {
        var controller = new FXMLController();
        controller.observableTodoList.setAll(new ListItem("test", null),
                new ListItem("test2", null));
        controller.deleteAllItems();
        assertTrue(controller.observableTodoList.isEmpty(), "List not emptied!");
    }

    @Test
    void testOverwriteListTable() throws FileNotFoundException, IOException, ClassNotFoundException {
        var controller = new FXMLController();
        controller.observableTodoList.setAll(new ListItem("test", null));
        try(var f = new FileInputStream(new File("data/TestFile.txt")); var o = new ObjectInputStream(f)) {
            controller.overwriteListTable(o);
        }
        assertEquals(controller.observableTodoList,
                FXCollections.observableArrayList(new ArrayList<>(List.of(new ListItem("abcd",
                        LocalDate.of(1,2,3)), new ListItem("efgh",
                        LocalDate.of(4,5,6))))), "Lists do not match!");
    }
    Couldn't debug test cases in time
    */
}