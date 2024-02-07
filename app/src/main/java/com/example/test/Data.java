package com.example.test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    public static ArrayList<Task> tasks;
    public static  ArrayList<Event> events;

    public static void sortByDate(ArrayList<Task> taskMasterList) { //CHECK
        for (int i = 1; i < taskMasterList.size(); i++) {
            Task temp = taskMasterList.get(i);
            int j = i - 1;
            while (j >= 0 && taskMasterList.get(j).compareTo(temp) > 0) {
                //the compare to method override in event
                taskMasterList.set(j + 1, taskMasterList.get(j));
                j--;
            }
            taskMasterList.set(j + 1, temp);
        }
    }

    public static void sortBytaskTitle(ArrayList<Task> taskMasterList) { //CHECK
        for (int i = 1; i < taskMasterList.size(); i++) {
            Task temp = taskMasterList.get(i);
            int j = i - 1;
            while (j >= 0 && taskMasterList.get(j).getTaskTitle().compareTo(temp.getTaskTitle()) > 0) {
                //Strings compareTo
                taskMasterList.set(j + 1, taskMasterList.get(j));
                j--;
            }
            taskMasterList.set(j + 1, temp);
        }
    }
}
