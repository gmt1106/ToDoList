package model;

import Exceptions.ToDoIsEmptyString;

import java.io.IOException;

public interface DailyItem {

    public void setDate(int date);
    public int getDate();
    public int getDailyFromList();
    public int getSizeOfDailyList();
    public void insertDaily(String Daily)  throws ToDoIsEmptyString;
    public void save(String title)throws IOException;
    public void load(String title) throws IOException;
    public void addItsDailyItemSet (DailyItemSet d);


}
