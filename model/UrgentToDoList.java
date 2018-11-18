package model;

import Exceptions.ToDoIsEmptyString;

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
        Integer num = Integer.parseInt(array[0]) - 1;
        String todo = array[1];

        listOfToDo.add(num , todo);
    }


    @Override
    public void addItsDailyItemSet (DailyItemSet d) {

        if (itsDailyItemSet == null) {
            itsDailyItemSet = d;
            d.addDailyItem(orderInDailyItemSet,this);
        }
    }


    public String getAllExistingToDoList(){

        String allToDo = "";

        for (int i = 0; i < listOfToDo.size(); i++) {

            if (i == 0) {

                allToDo = Integer.toString(i+1) + "/ " + listOfToDo.get(i);
            } else {

                allToDo =  allToDo + ", " + Integer.toString(i+1) + "/ " + listOfToDo.get(i);
            }
        }

        return ("current urgent To-do List " + ": " + allToDo);
    }

}
