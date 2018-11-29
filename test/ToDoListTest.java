package test;

import Exceptions.ToDoIsEmptyString;
import model.RegularToDoList;
import model.ToDoList;
import java.io.IOException;

import model.UrgentToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {

    private ToDoList regularToDoListForTesting;
    private ToDoList regularToDoListForLoading;
    private ToDoList urgentToDoListForTesting;


    private int dateForTesting = 911;
    private String toDoForTesting0 = "Task0";

    private String toDoForTesting1 = "Task1";
    private String toDoForTesting2 = "Task2";


    @BeforeEach
    public void runBeforeToDoListTest() {
        regularToDoListForTesting = new RegularToDoList();
        regularToDoListForLoading = new RegularToDoList();
        urgentToDoListForTesting = new UrgentToDoList();
    }

    //TODO: test setDate to see if the date is set as the parameter
    //TODO: try to re-set it and check if it is re-set
    @Test
    public void testSetDate() {

//        dateForTesting = 910;
//        regularToDoListForTesting.setDate(dateForTesting);
//        assertEquals(regularToDoListForTesting.getDate(), dateForTesting);
//
//        dateForTesting = 1014;
//        regularToDoListForTesting.setDate(dateForTesting);
//        assertEquals(regularToDoListForTesting.getDate(), dateForTesting);

    }



    @Test
    public void testSaveAndLoadFile() throws IOException {
//
//        String fileTitle = "SeptemberEleven";
//        String expectedLoadingString = "0: Task0, Task1, Task2";
//        regularToDoListForTesting.setDate(dateForTesting);
//        try {
//            regularToDoListForTesting.insertDaily(toDoForTesting0);
//            regularToDoListForTesting.insertDaily(toDoForTesting1);
//            regularToDoListForTesting.insertDaily(toDoForTesting2);
//        }
//        catch (ToDoIsEmptyString t) {
//            fail("wrong excpetion is caught");
//        }
//
//        regularToDoListForTesting.save(fileTitle);
//        regularToDoListForLoading.load(fileTitle);

    }

    @Test
    public void testToDoIsEmptryStringException() {

        String emptyString = "";
        String emptySpaceString = "           ";

        try {
            regularToDoListForTesting.insertDaily(emptySpaceString);
            fail("didn't catch the expected exception");
        }
        catch(ToDoIsEmptyString t) {

        }
        try {
            regularToDoListForTesting.insertDaily(emptyString);
            fail("didn't catch the expected exception");
        }
        catch(ToDoIsEmptyString t) {

        }
    }

    @Test
    public void testUrgentToDoListAdding() {

        String todo1 = "1 happy birthday";
        String todo2 = "2 I want to throw my code away";
        String todo3 = "3 this is such a mess!!!!!!!";
        String todo4 = "1 this would work mam";

        try {urgentToDoListForTesting.insertDaily(todo1);
            urgentToDoListForTesting.insertDaily(todo2);
            urgentToDoListForTesting.insertDaily(todo3);
            urgentToDoListForTesting.insertDaily(todo4);
        }
        catch (ToDoIsEmptyString t) {
            fail("unexpected exception");
        }

        System.out.println(urgentToDoListForTesting.getAllExistingToDoList());
    }



}
