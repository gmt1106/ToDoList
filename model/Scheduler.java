package model;

import dates.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {

    private Map<Date, DailyItemSet> schedulerMap;
    private DDay dDays;

    public Scheduler() {

        schedulerMap = new HashMap<>();
        dDays = new DDay();
//        updateDDayDisplay();
    }


    public boolean isExistingDate(Date date) {

        DailyItemSet toDoListsOnGivenDate = schedulerMap.get(date);

        if(toDoListsOnGivenDate == null) {
            return false;
        }

        return true;
    }

    public DailyItemSet getToDoListsOnDate(Date date) {
        return schedulerMap.get(date);
    }

    public void setNewDateWithToDoLists(Date date, DailyItemSet threeToDoLists) {
        schedulerMap.put(date,threeToDoLists);
    }

    public void updateDDayDisplayInScheduler() {

        dDays.updateDDayDisplayInDDay();
    }


}
