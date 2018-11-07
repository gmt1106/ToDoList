package model;

import Exceptions.ToDoIsEmptyString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public abstract class ToDoList implements DailyItem {

    protected int date;
    protected ArrayList<String> listOfToDo;
    protected int toDoListTitle;
    protected DailyItemSet itsDailyItemSet = null;

    // EFFECT: make new listOfToDo
    public ToDoList() {
        listOfToDo = new ArrayList<>();
    }

    protected void setTitle(int title) {

        toDoListTitle = title;
    }

    @Override
    //REQUIRE: date need to be format of MM/DD or M/DD.
    //         can not be less than 101.
    //MODIFY: this
    //EFFECT: set date as parameter value
    public void setDate(int date) {
        this.date = date;
    }

    @Override
    //EFFECT: get date
    public int getDate() {
        return date;
    }

    @Override
    public int getDailyFromList() {
        return 0;
    }

    @Override
    public int getSizeOfDailyList() {
        return listOfToDo.size();
    }

    @Override
    //REQUIRE: to-do can not be empty string
    //MODIFY: this
    //EFFECT: add to-do in list of to-do
    public abstract void insertDaily(String todo)  throws ToDoIsEmptyString;

    @Override
    public void save(String title) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(Integer.toString(date));
        lines.addAll(listOfToDo);
        Files.write(Paths.get(title + ".txt"), lines);
    }

    @Override
    public void load(String title) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));
        String nums = lines.remove(0);
        this.date = Integer.parseInt(nums);

        listOfToDo.addAll(lines);
    }


    public boolean isEmpty() {

        if (listOfToDo.isEmpty()) {
            return true;
        }

        return false;
    }

    //EFFECT: get to-do in list with the title
    public String getAllToDoWithTitle(){

        String allToDo = "";

        for (int i = 0; i < listOfToDo.size(); i++) {

            if (i == 0) {

                    allToDo = listOfToDo.get(0);
            } else {

                    allToDo = allToDo + ", " + listOfToDo.get(i);
            }
        }

        return (toDoListTitle + ": " + allToDo);
    }

    protected boolean stringOnlyContainSpaces(String s) {

        String[] array = s.split(" ");
        for(String space : array) {
            if(!s.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
