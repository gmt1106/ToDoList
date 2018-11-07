package model;

import Exceptions.ToDoIsEmptyString;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.RED;

public class UrgentToDoList extends ToDoList {


    //REQUIRE: to-do can not be empty string
    //MODIFY: this
    //EFFECT: add to-do in list of to-do in given order
    @Override
    public void insertDaily(String numTodo) throws ToDoIsEmptyString {

        if(numTodo.isEmpty() || stringOnlyContainSpaces(numTodo)) {
            throw new ToDoIsEmptyString();
        }

        String[] array = numTodo.split(" ", 2);
        Integer num = Integer.parseInt(array[0]);
        String todo = array[1];

        listOfToDo.add(num,todo);
    }


    @Override
    public void addItsDailyItemSet (DailyItemSet d) {

        if (itsDailyItemSet == null) {
            itsDailyItemSet = d;
            d.addUrgentToDoList(this);
        }
    }


    public String getAllToDoInUrgentToDoList(){

        String allToDo = "";

        for (int i = 0; i < listOfToDo.size(); i++) {

            if (i == 0) {

                allToDo = listOfToDo.get(0);
            } else {

                allToDo =  allToDo + ", " + Integer.toString(i) + listOfToDo.get(i);
            }
        }

        return (toDoListTitle + ": " + allToDo);
    }

}
