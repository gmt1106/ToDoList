package model;

import observer.DDayUpdate;
import dates.Date;

import java.util.*;

public class Scheduler implements Iterable<Date> {

    private Map<Date, DailyItemSet> schedulerMap = new HashMap<>();
    private DDayManager dDayManager;

    public Scheduler(DDayUpdate dDayUpdate) {

        dDayManager = new DDayManager(this, dDayUpdate);
    }


    public boolean isExistingDate(Date date) {

        DailyItemSet toDoListsOnGivenDate = schedulerMap.get(date);

        if(toDoListsOnGivenDate == null) {
            return false;
        }

        return true;
    }

    public DailyItemSet getToDoListsOnDate(Date date) {
        return schedulerMap.get(date);
    }

    public void setNewDateWithToDoLists(Date date, DailyItemSet threeToDoLists) {
        schedulerMap.put(date,threeToDoLists);
    }

    public void updateDDayDisplayInScheduler() {

        dDayManager.updateDDayDisplayInNewDDay();

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Date> iterator() {
        Set<Date> dates = schedulerMap.keySet();
        return dates.iterator();
    }

    public void deleteOldDDay(Date date) {
        schedulerMap.get(date).deleteOldDDay();
    }


    //    public void save(String title) throws IOException {
//
//        // [1,2,3,4,5] => 1 2 3 4 5
//
//        // 1 2 3 4 5
//        // task1
//        // task2
//        // task3
//
//        PrintWriter writer = new PrintWriter(new FileWriter(new File(title +".txt")));
//        StringBuilder builder = new StringBuilder();
//        for(Integer i : listOfDDayDate) {
//            builder.append(i);
//            builder.append(" ");
//        }
//        List<String> lines = new ArrayList<>();
//        lines.add(builder.toString().trim());
//        lines.addAll(listOfDDay);
//        for(String line : lines) {
//            writer.println(line);
//        }
//        writer.close();
//    }
//
//
//    public void load(String title) throws IOException {
//
//        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));
//
//        // task1
//        // task2
//        // task3
//
//        String nums = lines.remove(0);
//
//        String[] array = nums.split(" ");
//        for(String number : array) {
//            listOfDDayDate.add(Integer.parseInt(number));
//        }
//        listOfDDay.addAll(lines);
//    }


}
