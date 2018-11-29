package model;

import Exceptions.ToDoIsEmptyString;

import java.io.IOException;
import java.util.List;

public interface DailyItem {

    public int getDailyFromList();
    public int getSizeOfDailyList();
    public void insertDaily(String Daily)  throws ToDoIsEmptyString;
    public void save(String title)throws IOException;
    public void load(String title) throws IOException;
    public void addItsDailyItemSet (DailyItemSet d);
    public String getAllExistingToDoList();
    public void setOrderInDailyItemSet(int order);
    public void deleteOldDDay();
    public List<String> getListOfToDo();
    public void setRangeForUrgentToDoList(int i);
}
