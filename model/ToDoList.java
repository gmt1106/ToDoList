package model;

import Exceptions.ToDoIsEmptyString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public abstract class ToDoList implements DailyItem {

    protected ArrayList<String> listOfToDo;
    protected int toDoListTitle;
    protected DailyItemSet itsDailyItemSet = null;
    protected int orderInDailyItemSet;

    // EFFECT: make new listOfToDo
    public ToDoList() {
        listOfToDo = new ArrayList<>();
    }

    protected void setTitle(int title) {

        toDoListTitle = title;
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
        Files.write(Paths.get(title + ".txt"), listOfToDo);
    }

    @Override
    public void load(String title) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));

        listOfToDo.addAll(lines);
    }


    public boolean isEmpty() {

        if (listOfToDo.isEmpty()) {
            return true;
        }

        return false;
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

    public void setOrderInDailyItemSet(int order) {
        this.orderInDailyItemSet = order;
    }

}
