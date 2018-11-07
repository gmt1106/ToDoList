package dates;

import java.util.ArrayList;
import java.util.Objects;

public class Date {

    private Integer year;
    private Integer month;
    private String day;
    private Integer date;
    private ArrayList<String> dueThings;

    public Date(int year, int month, int date){

        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(year, date1.year) &&
                Objects.equals(month, date1.month) &&
                Objects.equals(day, date1.day) &&
                Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, day, date);
    }
}
