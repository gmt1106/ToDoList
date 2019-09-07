package ui;

import Exceptions.ToDoIsEmptyString;
import observer.DDayUpdate;
import model.*;
import dates.Date;
import javax.swing.*;

import java.util.Scanner;


public class LoggingSchedule extends JFrame {
    private Scanner scanner = new Scanner(System.in);
    private Integer intYear;
    private Integer intMonth;
    private Integer intDate;
    private String typeToDoList = "";
    private DailyItem inputToDoList;
    private DailyItemSet inputDailyItemSet = new DailyItemSet();
    private Date inputDate;
    private Scheduler userScheduler;
    private DDayUpdate dDayUpdate= new DDayUpdate();



    public static void main(String[] args) {

        new LoggingSchedule();
    }


    private LoggingSchedule() {

        userScheduler = new Scheduler(dDayUpdate);

        while (true) {

            displayDDay();

            String strStart = initiateOrQuit();

            if ("quit".equals(strStart)) {
                break;

            } else {

                setDateForGeneratedObject();


                if (!userScheduler.isExistingDate(inputDate = new Date(intYear, intMonth, intDate))) {

                    insertNewScheduleForNewDate();

                } else {

                    insertNewScheduleForOldDate();

                }

            }

        }

    }


    private void displayDDay() {

        System.out.println("Welcome to Scheduler!!");
        System.out.println("There is " + dDayUpdate.getNumberOfDDay() + "number of D-Days");
        System.out.println("Following list is D-Days.");
        userScheduler.updateDDayDisplayInScheduler();

    }


    private void setDateForGeneratedObject() {

        boolean initialLoopSate;
        String strYear;
        String strMonth;
        String strDate;

        do {
            System.out.println("Please give year in YYYY form.");
            strYear = scanner.nextLine();
            System.out.println("Please give month in MM form.");
            strMonth = scanner.nextLine();
            System.out.println("Please give date in DD form.");
            strDate = scanner.nextLine();

            try {
                Integer.parseInt(strYear);
                Integer.parseInt(strMonth);
                Integer.parseInt(strDate);

                initialLoopSate = false;
            } catch (NumberFormatException n) {

                initialLoopSate = true;
                System.out.println("Your input is not in valid form.");
            }
        } while (initialLoopSate);

        this.intYear = Integer.parseInt(strYear);
        this.intMonth = Integer.parseInt(strMonth);
        this.intDate = Integer.parseInt(strDate);

    }

    private void insertToDoStringInList() {

        boolean loopState;
        String stringToDo;

        do {
            switch (typeToDoList) {
                case "urgent":
                    System.out.println("Please write what you need to do on that date with priority with following format: number/ space/ todo");
                    break;
                case "regular":
                    System.out.println("Please write what you need to do on that date");
                    break;
                default:
                    System.out.println("Please write d-day title");
                    break;
            }

            stringToDo = scanner.nextLine();

            try {
                inputToDoList.insertDaily(stringToDo);
                loopState = false;
            } catch (ToDoIsEmptyString | NumberFormatException t) {

                loopState = true;
                System.out.println("Your input is not in valid form.");
            }
        }while(loopState);


    }

    private String initiateOrQuit() {

        boolean initialLoopState;
        String strStart;

        do {
            initialLoopState = false;

            System.out.println("Please type -start- to add new schedule or type -quit- if you are done");
            strStart = scanner.nextLine();

            if (!"quit".equals(strStart) && !"start".equals(strStart)) {
                initialLoopState = true;
                System.out.println("Your input is not in valid form.");
            }

        } while (initialLoopState);

        return strStart;
    }

    private void insertNewScheduleForNewDate() {

        boolean notValidInput;

        userScheduler.setNewDateWithToDoLists(inputDate, inputDailyItemSet);

        System.out.println("There is not existing schedule on this date! What do you want to add?");

        do {
            notValidInput = false;

            System.out.println("Is it urgent todo or regular todo or D-Day? type urgent or regular or dday");
            typeToDoList = scanner.nextLine();

            if(!typeToDoList.equals("urgent") && !typeToDoList.equals("regular") && !typeToDoList.equals("dday")) {

                notValidInput = true;
                System.out.println("Your input is not in valid form.");
            }

        }while(notValidInput);

        inputDailyItemSet = userScheduler.getToDoListsOnDate(inputDate);

        switch (typeToDoList) {
            case "urgent":

                inputToDoList = inputDailyItemSet.getUrgentToDoList();
                break;
            case "regular":

                inputToDoList = inputDailyItemSet.getRegularToDoList();
                break;
            case "dday":

                inputToDoList = inputDailyItemSet.getDDay();

                break;
        }

        insertToDoStringInList();
    }



    private void insertNewScheduleForOldDate() {

        boolean notValidInput;

        inputDailyItemSet = userScheduler.getToDoListsOnDate(inputDate);


        System.out.println("There is saved schedule on this date! What do you want to add?");

        do {
            notValidInput = false;

            System.out.println("Is it urgent todo or regular todo or D-day? type urgent or regular or dday");
            typeToDoList = scanner.nextLine();

            if (!"urgent".equals(typeToDoList) && !"regular".equals(typeToDoList)  && !"dday".equals(typeToDoList)) {
                notValidInput = true;
                System.out.println("Your input is not in valid form.");
            }

        }while (notValidInput);


        switch (typeToDoList) {
            case "urgent":

                inputToDoList = inputDailyItemSet.getUrgentToDoList();

                break;
            case "regular":

                inputToDoList = inputDailyItemSet.getRegularToDoList();
                break;
            case "dday":

                inputToDoList = inputDailyItemSet.getDDay();
                break;
            default:

                break;
        }


        System.out.println("Following is the todo list that already added:");
        String existingToDoList = inputToDoList.getAllExistingToDoList();
        System.out.println(existingToDoList);

        insertToDoStringInList();
    }


}
