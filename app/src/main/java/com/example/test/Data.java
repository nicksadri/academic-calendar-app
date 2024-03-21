package com.example.test;

import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Exam> exams = new ArrayList<>();
    public static int pos = 0;

    public static void deleteToDoAtPos(int pos) {
        Task toBeRemoved = tasks.get(pos);
        tasks.remove(toBeRemoved);
//        for (int i = 0; i < tasks.size() - 1; i++) {
//            Task temp = tasks.get(i + 1);
//            tasks.set(i, temp);
//        }
    }

    public static List<Task> sortByDate() { //CHECK
        for (int i = 1; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            int j = i - 1;
            while (j >= 0 && tasks.get(j).compareTo(temp) > 0) {
                //the compare to method override in event
                tasks.set(j + 1, tasks.get(j));
                j--;
            }
            tasks.set(j + 1, temp);
        }
        return tasks;
    }

    public static List<Task> sortByTaskTitle() { //CHECK
        for (int i = 1; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            int j = i - 1;
            while (j >= 0 && tasks.get(j).getTaskTitle().compareTo(temp.getTaskTitle()) > 0) {
                //Strings compareTo
                tasks.set(j + 1, tasks.get(j));
                j--;
            }
            tasks.set(j + 1, temp);
        }
        return tasks;
    }

}
    
