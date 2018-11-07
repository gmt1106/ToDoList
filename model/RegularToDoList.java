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
            d.addRegularToDoList(this);
        }
    }
}
