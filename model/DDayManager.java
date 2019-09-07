package model;

import observer.DDayUpdate;
import dates.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

public class DDayManager extends Observable {


    private LocalDate now;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private String todayDate;
    private Date todayDateObject;
    private Scheduler scheduler;
    private String returnString;

    public DDayManager(Scheduler scheduler, DDayUpdate dDayUpdate) {

        addObserver(dDayUpdate);
        this.scheduler = scheduler;
    }

    private void updateTodayDateObject() {

        now = LocalDate.now();
        todayDate = now.format(dtf);
        String [] todayDateList = todayDate.split("/");
        todayDateObject = new Date(Integer.parseInt(todayDateList[0]),Integer.parseInt(todayDateList[1]),Integer.parseInt(todayDateList[2]));
    }

    public void deleteAllOldDDays() {

        updateTodayDateObject();

        for(Date date : scheduler) {

            if(!date.isLaterDate(todayDateObject)) {

                scheduler.deleteOldDDay(date);
            }
        }

    }

    public String getReturnString() {
        return returnString;
    }

    public void updateDDayDisplayInNewDDay() {

        deleteAllOldDDays();

        for(Date date : scheduler) {

            Integer dDay = date.calculateNumberOfDaysLeft(todayDateObject);
            returnString = scheduler.getToDoListsOnDate(date).getDDay().getAllExistingToDoList();

            setChanged();
            notifyObservers(dDay);

        }

    }

}
