package model;

import dates.Date;
import java.util.HashMap;
import java.util.Map;

public class Scheduler {

    private Map<Date, DailyItemSet> schedulerMap;

    public Scheduler() {

        schedulerMap = new HashMap<>();

    }

    public boolean existingDate (Date d) {
        return false;
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

}
