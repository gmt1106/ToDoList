package test;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.DDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DDayTest {

    private DDay testDDayTesting;
    private DDay testDDayLoading;

    private int testDDayDate0 = 1015;
    private String testDDay0 = "CPSC210 assignment1";

    private int testDDayDate1 = 1021;
    private String testDDay1 = "STAT200 assignment1";


    @BeforeEach
    public void runBeforeDDayTest(){

        testDDayTesting = new DDay();
        testDDayLoading = new DDay();
    }


    @Test
    public void testSaveAndLoadFile() throws IOException {

        String fileTitle = "SeptemberElevenDDay";

        testDDayTesting.insertDaily(testDDay0);
        testDDayTesting.insertDaily(testDDay1);
        testDDayTesting.insertDayDate(testDDayDate0);
        testDDayTesting.insertDayDate(testDDayDate1);

        testDDayTesting.save(fileTitle);
        testDDayLoading.load(fileTitle);

        for (int i=0; i < testDDayTesting.getSizeOfDailyList(); i++ ) {

            assertEquals(testDDayLoading.getDDatesFromListOfDDayDate(i),testDDayTesting.getDDatesFromListOfDDayDate(i));
        }
    }

}
