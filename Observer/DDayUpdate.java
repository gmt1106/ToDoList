package Observer;

import model.DDayManager;

import java.util.*;

public class DDayUpdate implements Observer{

    private int numberOfDDay;
    private List<String> outPutList = new ArrayList<>();

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

        DDayManager dDayManager = (DDayManager) o;
        Integer dDate = (Integer) arg;

        String DDays = dDayManager.getReturnString();
        String[] listOfDDays = DDays.split("/");

        numberOfDDay = numberOfDDay + listOfDDays.length;


        for(String dDay : listOfDDays) {
            outPutList.add(dDate + " - " + dDay);
        }

    }

    public Integer getNumberOfDDay() {
        return numberOfDDay;
    }

    public List<String> outPutDDaysWithDate() {

        return outPutList;
    }
}
