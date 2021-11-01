package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListGroupTest {
    //Note that this is the only class that really needs to be tested, as all the others just call its or its lists'
    //methods.
    //Furthermore, the only methods not listed here are just set and get methods.

    @Test
    void writeListsToFile() {
        //First, create a TodoListGroup to test with.
        /*Then, add a list named "testList" with two items: One named "testItem" with a dueDate of 01/02/2003 and
        the contents "test test test\ntest test test", and another named "testItem2" with a dueDate of 04/05/2006 and
        the contents "You shouldn't be reading this.".*/
        //Then, write to the file data/app1_test_output.txt using writeListsToFile(path, List.of(true, false)).
        //If the file contains the first list and only the first list, then the test is successful.
    }

    @Test
    void readListsFromFile() {
        /*First, create a file containing a list named "testList" with a single item named "testItem" with a dueDate of
        07/08/2009 and the contents "This is a test." at data/app_test_input.txt.*/
        //Then, create a TodoListGroup to test with.
        //Then, call readListsFromFile(path) to read in the file.
        /*Finally, if the first and only list is testList and the first and only item is testItem, then the test is
        successful.*/
    }

    @Test
    void addList() {
        //First, create a TodoListGroup for testing.
        //Then, call addList("testList").
        //If the first and only list is testList, then the test is successful.
    }

    @Test
    void deleteList() {
        //First, create a TodoListGroup for testing that contains three lists: testList1, testList2, and testList3.
        //Then, call deleteList(1).
        //If the TodoListGroup not only contains testList1 and testList3, then the test is successful.
    }

    @Test
    void addItemToList() {
        //First, create a TodoListGroup for testing that contains one list named testList.
        //Then, call addItemToList(0, "testItem", [calendar for 01/02/2003]).
        /*If the first item of testList now has the description testItem and the dueDate 01/02/2003, then the test is
        successful.*/
    }

    @Test
    void deleteItemFromList() {
        /*First, create a TodoListGroup for testing that contains one list named testList with three items: testItem1,
        testItem2, and testItem3.*/
        //Then, call deleteItemFromList(0, 1).
        //If testList now only contains testItem1 and testItem3, then the test is successful.
    }
}