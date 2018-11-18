package Observer;

import model.NewDDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DDayUpdate implements Observer{

    private int numberOfDDay;

    public DDayUpdate(){

    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

        numberOfDDay++;

        NewDDay newDDay = (NewDDay) arg;
        Integer dDate = newDDay.getDYear()*12*30 + newDDay.getDMonth()*30 + newDDay.getDDate();
        List<String> toDos = newDDay.getDToDo();

        for (int i = 0; i < toDos.size(); i++){
            System.out.println(toDos.get(i) + " - " + dDate);
        }
    }

    public Integer getNumberOfDDay() {
        return numberOfDDay;
    }
}
