package com.example.test;

import java.util.HashSet;

public class Course extends Event {
    private String professor;
    private String classSection;
    private String building_AND_room;
    private int startHour;
    private int startMinute;
    private HashSet<String> daysOfWeekSet;

    /**
     * @param year year of event
     * @param month month of event 1-12
     * @param day day of event 1-31
     * @param courseName name of course
     * @param professor name of professor
     * @param classSection section of class
     * @param building_AND_room building and room the class is in
     * @param startHour start time hour 0-23
     * @param startMinute end time 0-59
     * @param daysofWeek days of week the course is on
     */
    public Course(int year, int month, int day, String courseName, String professor, String classSection,
                  String building_AND_room, int startHour, int startMinute, String[] daysofWeek) {
        super(year, month, day, courseName);
        this.professor = professor;
        this.classSection = classSection;
        this.building_AND_room = building_AND_room;
        this.startHour = startHour;
        this.startMinute = startMinute;
        daysOfWeekSet = new HashSet<>();
        for (int i = 0; i < daysofWeek.length; i++) {
            daysOfWeekSet.add(daysofWeek[i]);
        }
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getClassSection() {
        return classSection;
    }

    public void setClassSection(String classSection) {
        this.classSection = classSection;
    }

    public String getBuilding_AND_room() {
        return building_AND_room;
    }

    public void setBuilding_AND_room(String building_AND_room) {
        this.building_AND_room = building_AND_room;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }


    public String getDaysOfWeekSetString() {
        StringBuilder s = new StringBuilder();
        s.append("Meets: ");
        for (String x : daysOfWeekSet) {
            s.append(x);
            s.append(" ");
        }
        return s.toString();
    }

    public HashSet<String> getDaysOfWeekSet() {
        return daysOfWeekSet;
    }

    public void setDaysOfWeekSet(HashSet<String> daysOfWeekSet) {
        this.daysOfWeekSet = daysOfWeekSet;
    }

    public String toString() {
        return super.toString() + "," + professor + "," + classSection + "," + building_AND_room+ "," + startHour +
                "," + startMinute;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass().equals(obj.getClass())) {
            Course cp = (Course) obj;
            return super.equals(obj) && this.professor.equals(cp.professor) && this.classSection.equals(cp.classSection)
                    && this.building_AND_room.equals(building_AND_room) &&
                    this.startHour == cp.startHour && this.startMinute == cp.startMinute;
        } else {
            return false;
        }
    }

    public int compareTo(Course o) {
        if (super.compareTo(o) == 0) {
            if (this.startHour < o.startHour) {
                return -1;
            } else if (this.startHour > o.startHour) {
                return 1;
            } else {
                if (this.startMinute < o.startMinute) {
                    return -1;
                } else if (this.startMinute > o.startMinute) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            return super.compareTo(o);
        }
    }
}