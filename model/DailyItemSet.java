package model;

import dates.Date;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DailyItemSet {

    private ArrayList<DailyItem> dailyItemSet;
    private DailyItem urgentToDoList;
    private int urgentIndex = 0;
    private DailyItem regularToDoList;
    private int regularIndex = 1;
    private DailyItem dDay;
    private int dDayIndex = 2;

    // EFFECT: make new schedule
    public DailyItemSet() {

        urgentToDoList = new UrgentToDoList();
        regularToDoList = new RegularToDoList();
        dDay = new DDay();

        dailyItemSet = new ArrayList<>();

        addDailyItem(urgentIndex,urgentToDoList);
        urgentToDoList.setOrderInDailyItemSet(urgentIndex);
        addDailyItem(regularIndex,regularToDoList);
        regularToDoList.setOrderInDailyItemSet(regularIndex);
        addDailyItem(dDayIndex,dDay);
        dDay.setOrderInDailyItemSet(dDayIndex);
    }

    public void addDailyItem(int order, DailyItem d) {

        if(!dailyItemSet.contains(d)) {
            dailyItemSet.add(order, d);
            d.addItsDailyItemSet(this);
        }
    }



    public DailyItem getUrgentToDoList() {

        return dailyItemSet.get(urgentIndex);
    }

    public DailyItem getRegularToDoList() {

        return dailyItemSet.get(regularIndex);
    }

    public DailyItem getDDay() {

        return dailyItemSet.get(dDayIndex);
    }

    public void deleteOldDDay() {

        dDay.deleteOldDDay();
    }

    public void save(String title) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("UrgentToDoList: ");
        Files.write(Paths.get(title + ".txt"), lines);
        urgentToDoList.save(title);

        lines.clear();
        lines.add("RegularToDoList: ");
        Files.write(Paths.get(title + ".txt"), lines);
        regularToDoList.save(title);

        lines.clear();
        lines.add("DDay: ");
        Files.write(Paths.get(title + ".txt"), lines);
        regularToDoList.save(title);

    }

    public void load(String title) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(title +".txt"));
        for(String line : lines) {

            if(line.equals("UrgentToDoList: ")) {
                urgentToDoList.load(title);
            }
            else if(line.equals("RegularToDoList: ")) {
                regularToDoList.load(title);
            }
            else if(line.equals("DDay: ")){
                dDay.load(title);
            }
        }
    }

}
