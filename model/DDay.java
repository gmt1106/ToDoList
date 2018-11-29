package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import dates.Date;
import java.util.ArrayList;
import java.util.List;

import Observer.DDayUpdate;
import dates.Date;


public class DDay implements DailyItem {

//    private ArrayList<String> listOfDDay;
//    private ArrayList<Integer> listOfDDayDate;
    private DailyItemSet itsDailyItemSet = null;
//    DateFormat dateFormat = new SimpleDateFormat("mm/dd");
    private int orderInDailyItemSet;
//    private int todayDate = 0;
//    private LocalDate now;
//    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");






    ///////////////////////
    private List<String> listOfDDaysForEachDate;



    // EFFECT : make new listOfToDo
    public DDay() {

 ;

//        listOfDDayDate = new ArrayList<Integer>();
//        listOfDDay = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
       // todayDate = Integer.parseInt(dateFormat.format(cal));


        listOfDDaysForEachDate = new ArrayList<>();


    }



//    public void updateDDayDisplayInNewDDay() {
//
//        now = LocalDate.now();
//        String todayDate = now.format(dtf);
//        String [] todayDateList = todayDate.split("/");
//
//        dYear = theDate.getYear() - Integer.parseInt(todayDateList[0]);
//        dMonth = theDate.getMonth() - Integer.parseInt(todayDateList[1]);
//        dDate = theDate.getDate() - Integer.parseInt(todayDateList[2]);
//
//        setChanged();
//        notifyObservers(this);
//    }


    @Override
    public void deleteOldDDay() {
        listOfDDaysForEachDate.clear();
    }

    @Override
    public List<String> getListOfToDo() {
        return null;
    }

    @Override
    public void setRangeForUrgentToDoList(int i) {

    }


    public void displayDDay(String[] todayDateList) {


    }


//    public void addDDayOnGivenDate (Date date, String dDay) {
//
//        for(int i = 0; i < listOfDDaysForEachDate.size(); i++) {
//            if (listOfDDaysForEachDate.get(i).getDate().equals(date)){
//
//                listOfDDaysForEachDate.get(i).addDDays(dDay);
//            }
//        }
//
//        NewDDay newDDay = new NewDDay(date);
//        newDDay.addDDays(dDay);
//        newDDay.addObserver(dDayUpdate);
//        listOfDDaysForEachDate.add(newDDay);
//    }


//    public void updateDDayDisplayInDDay() {
//
//        deleteAllOldDDays();
//
//        for(int i = 0; i < listOfDDaysForEachDate.size(); i++) {
//
//            listOfDDaysForEachDate.get(i).updateDDayDisplayInNewDDay();
//        }
//    }







    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //


    public int getDailyFromList() {
        return 0;
    }

//    public int getDDatesFromListOfDDayDate(int i) {
//
//        return listOfDDayDate.get(i);
//    }


    public int getSizeOfDailyList() {
        return listOfDDaysForEachDate.size();
    }

//
//    public int getSizeOfListOfDDayDate() {
//        return  listOfDDayDate.size();
//    }



    // EFFECT : add DDay into listOfDay
    public void insertDaily(String DDay) {

        listOfDDaysForEachDate.add(DDay);
    }



//    // EFFECT : add DDayDate into listOfDayDate
//    public void insertDayDate(int DDayDate) {
//
//        listOfDDayDate.add(DDayDate);
//    }


    public void save(String title) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(new File(title +".txt")));
        StringBuilder builder = new StringBuilder();
        List<String> lines = new ArrayList<>();
        lines.add(builder.toString().trim());
        lines.addAll(listOfDDaysForEachDate);
        for(String line : lines) {
            writer.println(line);
        }
        writer.close();
    }


    public void load(String title) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));
        listOfDDaysForEachDate.addAll(lines);
    }



//    public String getDDay() {
//
//        String dDay = "";
//        int daysLeft = 0;
//
//        for (int i = 0; i < listOfDDay.size(); i++) {
//
//            if (i == 0) {
//
//                daysLeft = todayDate - listOfDDayDate.get(0);
//                dDay = listOfDDay.get(0) + Integer.toString(daysLeft);
//            }
//            else {
//
//                dDay = dDay + ", " + listOfDDay.get(i);
//            }
//        }
//
//        return dDay;
//    }


    public void addItsDailyItemSet(DailyItemSet d) {

        if(itsDailyItemSet == null) {
            itsDailyItemSet = d;
            d.addDailyItem(orderInDailyItemSet, this);
        }
    }

    public String getAllExistingToDoList() {
        String returnString = "";
        for(String s : listOfDDaysForEachDate) {

            returnString = returnString + "/" + s;
        }
        return returnString;
    }

    public void setOrderInDailyItemSet(int order) {
        orderInDailyItemSet = order;
    }



}
