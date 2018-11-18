package model;

import Observer.DDayUpdate;
import com.sun.tools.corba.se.idl.InterfaceGen;
import dates.Date;
import javafx.beans.InvalidationListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NewDDay extends Observable {

    private Date theDate;
    private List<String> dToDo;
    private LocalDate now;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private Integer dYear;
    private Integer dMonth;
    private Integer dDate;


    public NewDDay(Date date) {

        theDate = date;
        dToDo = new ArrayList<>();
    }

    public void addDDays(String dday) {

        dToDo.add(dday);
    }

    public Date getDate() {

        return theDate;
    }


    public void updateDDayDisplayInNewDDay() {

        now = LocalDate.now();
        String todayDate = now.format(dtf);
        String [] todayDateList = todayDate.split("/");

        dYear = theDate.getYear() - Integer.parseInt(todayDateList[0]);
        dMonth = theDate.getMonth() - Integer.parseInt(todayDateList[1]);
        dDate = theDate.getDate() - Integer.parseInt(todayDateList[2]);

        setChanged();
        notifyObservers(this);
    }

    public Integer getDYear() {
        return dYear;
    }

    public Integer getDMonth() {
        return dMonth;
    }

    public Integer getDDate() {
        return dDate;
    }

    public List getDToDo() {
        return dToDo;
    }


}
