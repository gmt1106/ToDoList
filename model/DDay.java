package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DDay implements DailyItem {

    private ArrayList<String> listOfDDay;
    private ArrayList<Integer> listOfDDayDate;
    private DailyItemSet itsDailyItemSet = null;
    DateFormat dateFormat = new SimpleDateFormat("mm/dd");

    int todayDate = 0;

    // EFFECT : make new listOfToDo
    public DDay() {
        listOfDDay = new ArrayList<>();
        listOfDDayDate = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
       // todayDate = Integer.parseInt(dateFormat.format(cal));

    }

    @Override
    //
    // EFFECT : add dDate that is setted for dDay
    public void setDate(int dDate) {

        listOfDDayDate.add(dDate);
    }

    @Override
    // EFFECT : return the date of today
    public int getDate() {
        return todayDate;
    }

    @Override
    public int getDailyFromList() {
        return 0;
    }

    public int getDDatesFromListOfDDayDate(int i) {

        return listOfDDayDate.get(i);
    }

    @Override
    public int getSizeOfDailyList() {
        return listOfDDay.size();
    }


    public int getSizeOfListOfDDayDate() {
        return  listOfDDayDate.size();
    }


    @Override
    // EFFECT : add DDay into listOfDay
    public void insertDaily(String DDay) {

        listOfDDay.add(DDay);
    }



    // EFFECT : add DDayDate into listOfDayDate
    public void insertDayDate(int DDayDate) {

        listOfDDayDate.add(DDayDate);
    }

    @Override
    public void save(String title) throws IOException {

        // [1,2,3,4,5] => 1 2 3 4 5

        // 1 2 3 4 5
        // task1
        // task2
        // task3

        PrintWriter writer = new PrintWriter(new FileWriter(new File(title +".txt")));
        StringBuilder builder = new StringBuilder();
        for(Integer i : listOfDDayDate) {
            builder.append(i);
            builder.append(" ");
        }
        List<String> lines = new ArrayList<>();
        lines.add(builder.toString().trim());
        lines.addAll(listOfDDay);
        for(String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    @Override
    public void load(String title) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));

        // task1
        // task2
        // task3

        String nums = lines.remove(0);

        String[] array = nums.split(" ");
        for(String number : array) {
            listOfDDayDate.add(Integer.parseInt(number));
        }
        listOfDDay.addAll(lines);
    }


    public String getDDay() {

        String dDay = "";
        int daysLeft = 0;

        for (int i = 0; i < listOfDDay.size(); i++) {

            if (i == 0) {

                daysLeft = todayDate - listOfDDayDate.get(0);
                dDay = listOfDDay.get(0) + Integer.toString(daysLeft);
            }
            else {

                dDay = dDay + ", " + listOfDDay.get(i);
            }
        }

        return dDay;
    }

    @Override
    public void addItsDailyItemSet(DailyItemSet d) {

        if(itsDailyItemSet == null) {
            itsDailyItemSet = d;
            d.addDDay(this);
        }
    }
}
