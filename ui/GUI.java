package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Exceptions.ToDoIsEmptyString;
import Observer.DDayUpdate;
import model.*;
import dates.Date;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame {

    private Scanner scanner = new Scanner(System.in);
    private java.util.Date javaDate;
    private String javaDateString;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private String typeToDoList = "";
    private Scheduler userScheduler;
    private DDayUpdate dDayUpdate= new DDayUpdate();
    private String stringToDo;
    private String orderForUrgentToDoString = "";
    private Integer orderForUrgentToDo;
    private Integer rangeOfUrgentToDoList = 5;

    private JPanel GUI;
    private JButton buttonDDay;
    private JButton buttonIntakeInPut;
    private JTextField monthTextField;
    private JTextField dayTextField;
    private JComboBox<String> comboBoxTypeOfItem;
    private JTextField yearTextField;
    private JPanel jPanelLabelTypeOfItem;
    private JPanel jPanelTypeOfItemComboBox;
    private JPanel jPanelLabelDate;
    private JPanel jPlaneSpinnerDate;
    private JLabel jLabelTypeOfItem;
    private JPanel jPanelDDayButton;
    private JPanel jPanelForEnterButton;
    private JLabel jLabelForDDay;
    private JSpinner jSpinnerDateInPut;
    private JLabel jLabelForToDoInputTextField;
    private JTextField jTextFieldToDoInput;
    private JSpinner jSpinnerDateOutPut;
    private JTextArea textAreaDDayDisplay;
    private JTextArea textAreaRegularToDoDisplay;
    private JTextArea textAreaUrgentToDoDisplay;
    private JButton buttonReCallAddedToDo;
    private JComboBox comboBoxForUrgentToDo;
    private JLabel jLabelForWorningMessage;
    private SpinnerDateModel inputSpinnerDateModel = new SpinnerDateModel();
    private SpinnerDateModel outputSpinnerDateModel = new SpinnerDateModel();



    public GUI() {


        buttonIntakeInPut.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                typeToDoList = (String) comboBoxTypeOfItem.getSelectedItem();
                stringToDo = jTextFieldToDoInput.getText();

                if(typeToDoList == null || stringToDo.isEmpty()) {
                    jLabelForWorningMessage.setText("You did not set type of ToDo or did not write anything on todo");
                }
                else if(typeToDoList.equals("Urgent To-Do List") && comboBoxForUrgentToDo == null) {
                    jLabelForWorningMessage.setText("You did not set order for urgent todo");
                }
                else {

                    orderForUrgentToDoString = (String) comboBoxForUrgentToDo.getSelectedItem();
                    orderForUrgentToDo = Integer.parseInt(orderForUrgentToDoString);

                    jLabelForWorningMessage.setText(null);

                    javaDate = (java.util.Date) jSpinnerDateInPut.getValue();
                    javaDateString = dateFormat.format(javaDate);

                    String [] inputDateList = javaDateString.split("/");
                    Date inputDate = new Date(Integer.parseInt(inputDateList[0]),Integer.parseInt(inputDateList[1]),Integer.parseInt(inputDateList[2]));

                    loggingSchedule(inputDate);
                }

            }
        });


        buttonDDay.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {


                textAreaDDayDisplay.selectAll();
                textAreaDDayDisplay.replaceSelection("");

                textAreaDDayDisplay.setText("The number of D-Day : " + Integer.toString(dDayUpdate.getNumberOfDDay()));
                userScheduler.updateDDayDisplayInScheduler();
                for(String s : dDayUpdate.outPutDDaysWithDate()) {

                    textAreaDDayDisplay.append("\n" + s);
                }


            }
        });
        buttonReCallAddedToDo.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                textAreaUrgentToDoDisplay.selectAll();
                textAreaUrgentToDoDisplay.replaceSelection("");
                textAreaRegularToDoDisplay.selectAll();
                textAreaRegularToDoDisplay.replaceSelection("");


                javaDate = (java.util.Date) jSpinnerDateOutPut.getValue();
                javaDateString = dateFormat.format(javaDate);
                String [] outputDateList = javaDateString.split("/");
                Date outputDate = new Date(Integer.parseInt(outputDateList[0]),Integer.parseInt(outputDateList[1]),Integer.parseInt(outputDateList[2]));

                if(!userScheduler.isExistingDate(outputDate)) {

                    textAreaRegularToDoDisplay.setText("There isn't existing Regular To-Do List on this date");
                    textAreaUrgentToDoDisplay.setText("There isn't existing Urgent To-Do List on this date 1");
                }
                else {
                    List<String> calledUrgentToDoList = userScheduler.getToDoListsOnDate(outputDate).getUrgentToDoList().getListOfToDo();
                    List<String> calledRegularToDoList = userScheduler.getToDoListsOnDate(outputDate).getRegularToDoList().getListOfToDo();

                    if(calledRegularToDoList.isEmpty()) {
                        textAreaRegularToDoDisplay.setText("There isn't existing Regular To-Do List on this date");

                    }
                    else {
                        for(String s : calledRegularToDoList){

                            textAreaRegularToDoDisplay.append("\n" + s);
                        }
                    }

                    if(calledUrgentToDoList.isEmpty()) {
                        textAreaUrgentToDoDisplay.setText("There isn't existing Urgent To-Do List on this date 2");

                    }
                    else {

                        for(int i = 0; i < calledUrgentToDoList.size(); i++){

                            if(calledUrgentToDoList.get(i) == null) {

                                textAreaUrgentToDoDisplay.append("\n" + (i+1) + " ");
                            }
                            else {

                                textAreaUrgentToDoDisplay.append("\n" + (i+1) + " " + calledUrgentToDoList.get(i));
                            }
                        }

                    }

                }
            }
        });
    }


    public static void main(String[] args) {

        GUI gui = new GUI();
        gui.userScheduler = new Scheduler(gui.dDayUpdate);

        gui.add(gui.GUI);
        gui.setTitle("Scheduler");

        gui.jSpinnerDateInPut.setModel(gui.inputSpinnerDateModel);
        gui.jSpinnerDateInPut.setEditor(new JSpinner.DateEditor(gui.jSpinnerDateInPut, "yyyy/MM/dd"));

        gui.jSpinnerDateOutPut.setModel(gui.outputSpinnerDateModel);
        gui.jSpinnerDateOutPut.setEditor(new JSpinner.DateEditor(gui.jSpinnerDateOutPut, "yyyy/MM/dd"));

        gui.comboBoxTypeOfItem.addItem("D-Day");
        gui.comboBoxTypeOfItem.addItem("Regular To-Do List");
        gui.comboBoxTypeOfItem.addItem("Urgent To-Do List");
        gui.comboBoxTypeOfItem.setSelectedItem(null);


        for(int i = 1; i <= gui.rangeOfUrgentToDoList; i++ ) {

            gui.comboBoxForUrgentToDo.addItem(Integer.toString(i));
        }
        gui.comboBoxForUrgentToDo.setSelectedItem(null);


        gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);
        gui.setSize(900, 900);
        gui.setVisible(true);

    }

    private void loggingSchedule(Date inputDate) {

        if (!userScheduler.isExistingDate(inputDate)) {

            DailyItemSet inputDailyItemSet = new DailyItemSet();
            userScheduler.setNewDateWithToDoLists(inputDate, inputDailyItemSet);
            insertNewToDo(inputDate);
        } else {

            insertNewToDo(inputDate);
        }

    }


    private void insertNewToDo(Date inputDate) {

        DailyItemSet inputDailyItemSet = userScheduler.getToDoListsOnDate(inputDate);
        DailyItem inputToDoList;

        switch (typeToDoList) {
            case "Urgent To-Do List":

                inputToDoList = inputDailyItemSet.getUrgentToDoList();
                stringToDo = Integer.toString(orderForUrgentToDo)+ " " + stringToDo;
                userScheduler.getToDoListsOnDate(inputDate).getUrgentToDoList().setRangeForUrgentToDoList(rangeOfUrgentToDoList);
                insertToDoStringInList(inputToDoList);

                break;
            case "Regular To-Do List":

                inputToDoList = inputDailyItemSet.getRegularToDoList();
                insertToDoStringInList(inputToDoList);

                break;
            case "D-Day":

                inputToDoList = inputDailyItemSet.getDDay();
                insertToDoStringInList(inputToDoList);

                break;
        }
    }

    private void insertToDoStringInList(DailyItem inputToDoList) {
        try {
            inputToDoList.insertDaily(stringToDo);

        } catch (ToDoIsEmptyString toDoIsEmptyString) {

            jLabelForWorningMessage.setText("You did not put anything on Todo");
        }

    }


}
