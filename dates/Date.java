package dates;

import java.util.ArrayList;
import java.util.Objects;

public class Date {

    private Integer year;
    private Integer month;
    private Integer date;
    private ArrayList<String> dueThings;

    public Date(int year, int month, int date){

        this.year = year;
        this.month = month;
        this.date = date;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(year, date1.year) &&
                Objects.equals(month, date1.month) &&
                Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, date);
    }

    public boolean IsLaterDate(Date date) {

        if (this.year > date.year || this.month > date.month || this.date > date.date) {
            return true;
        }

        return false;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }
    public Integer getDate() {
        return date;
    }
}
