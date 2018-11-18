package model;

import Exceptions.ToDoIsEmptyString;

import java.util.List;

public class RegularToDoList extends ToDoList {


    @Override
    //REQUIRE: to-do can not be empty string
    //MODIFY: this
    //EFFECT: add to-do in list of to-do
    public void insertDaily(String todo) throws ToDoIsEmptyString {

        if(todo.isEmpty() || stringOnlyContainSpaces(todo)) {
            throw new ToDoIsEmptyString();
        }

        listOfToDo.add(todo);
    }

    @Override
    public void addItsDailyItemSet (DailyItemSet d) {

        if (itsDailyItemSet == null) {
            itsDailyItemSet = d;
            d.addDailyItem(orderInDailyItemSet, this);
        }
    }

    @Override
    public String getAllExistingToDoList() {

        String allToDo = "";

        for (int i = 0; i < listOfToDo.size(); i++) {

            if (i == 0) {

                allToDo = listOfToDo.get(0);
            } else {

                allToDo =  allToDo + ", " + listOfToDo.get(i);
            }
        }

        return ("current regular To-do List " + ": " + allToDo);
    }



}

