package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Observer.DDayUpdate;
import dates.Date;
import javafx.beans.Observable;

public class DDay {

    private ArrayList<String> listOfDDay;
    private ArrayList<Integer> listOfDDayDate;
    private DailyItemSet itsDailyItemSet = null;
    DateFormat dateFormat = new SimpleDateFormat("mm/dd");
    private int orderInDailyItemSet;
    private int todayDate = 0;
    private LocalDate now;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private DDayUpdate dDayUpdate = new DDayUpdate();

    ///////////////////////
    private List<NewDDay> listOfDDaysForEachDate;


    // EFFECT : make new listOfToDo
    public DDay() {

 ;

        listOfDDayDate = new ArrayList<Integer>();
        listOfDDay = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
       // todayDate = Integer.parseInt(dateFormat.format(cal));


        listOfDDaysForEachDate = new ArrayList<>();

    }

//    public void addDDay(Date date, String dDay, int year, int month, int day) {
//
//        if(DDays.get(date) == null) {
//
//            Date standardDate;
//
//            listOfDDay = new ArrayList<>();
//            listOfDDay.add(dDay);
//            Date newDate = new Date(year, month, day);
//
//            List<Map.Entry<Date, List<String>>> entries = new ArrayList<Map.Entry<Date, List<String>>>(DDays.entrySet());
//            while (true) {
//                for (int i = 0; i < entries.size(); i++) {
//
//                    if (!newDate.IsLaterDate(entries.get(i).getKey())) {
//
//                        standardDate = entries.get(i).getKey();
//                        break;
//                    }
//                }
//            }
//
//
//
//        }
//        else {
//
//            DDays.get(date).add(dDay);
//        }
//    }

    public void displayDDay(String[] todayDateList) {


    }


    public void addDDayOnGivenDate (Date date, String dDay) {

        for(int i = 0; i < listOfDDaysForEachDate.size(); i++) {
            if (listOfDDaysForEachDate.get(i).getDate().equals(date)){

                listOfDDaysForEachDate.get(i).addDDays(dDay);
            }
        }

        NewDDay newDDay = new NewDDay(date);
        newDDay.addDDays(dDay);
        newDDay.addObserver(dDayUpdate);
        listOfDDaysForEachDate.add(newDDay);
    }


    public void updateDDayDisplayInDDay() {

        deleteAllOldDDays();

        for(int i = 0; i < listOfDDaysForEachDate.size(); i++) {

            listOfDDaysForEachDate.get(i).updateDDayDisplayInNewDDay();
        }
    }


    public void deleteAllOldDDays() {

        now = LocalDate.now();
        String todayDate = now.format(dtf);
        String [] todayDateList = todayDate.split("/");
        Date todayDateObject = new Date(Integer.parseInt(todayDateList[0]),Integer.parseInt(todayDateList[1]),Integer.parseInt(todayDateList[2]));

        for(int i = 0; i < listOfDDaysForEachDate.size(); i++) {

            if(!listOfDDaysForEachDate.get(i).getDate().IsLaterDate(todayDateObject)) {

                listOfDDaysForEachDate.remove(listOfDDaysForEachDate.get(i));
            }
        }

    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EFFECT : add dDate that is set for dDay
    public void setDate(int dDate) {

        listOfDDayDate.add(dDate);
    }



    public int getDailyFromList() {
        return 0;
    }

    public int getDDatesFromListOfDDayDate(int i) {

        return listOfDDayDate.get(i);
    }


    public int getSizeOfDailyList() {
        return listOfDDay.size();
    }


    public int getSizeOfListOfDDayDate() {
        return  listOfDDayDate.size();
    }



    // EFFECT : add DDay into listOfDay
    public void insertDaily(String DDay) {

        listOfDDay.add(DDay);
    }



    // EFFECT : add DDayDate into listOfDayDate
    public void insertDayDate(int DDayDate) {

        listOfDDayDate.add(DDayDate);
    }


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


//    public void addItsDailyItemSet(DailyItemSet d) {
//
//        if(itsDailyItemSet == null) {
//            itsDailyItemSet = d;
//            d.addDailyItem(orderInDailyItemSet, this);
//        }
//    }

    public String getAllExistingToDoList() {
        return "DDay does not provide list";
    }

    public void setOrderInDailyItemSet(int order) {
        orderInDailyItemSet = order;
    }


}
