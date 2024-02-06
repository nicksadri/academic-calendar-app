package com.example.test;

public abstract class Event implements Comparable<Event> {
    private int year;
    private int month;
    private int day;
    private String courseName;

    /**
     * @param year year of event
     * @param month month of event 1-12
     * @param day day of event 1-31
     * @param courseName name of course
     */
    public Event(int year, int month, int day, String courseName) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.courseName = courseName;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String toString() {
        return year + "," + month + "," + day + "," + courseName;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass().equals(obj.getClass())) {
            Event cp = (Event) obj;
            return this.year == cp.year && this.month == cp.month && this.day == cp.day &&
                    this.courseName.equals(cp.courseName);
        } else {
            return false;
        }
    }

    /**
     * based on the date of event compared to others.
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Event o) {
        if (this.year < o.year) {
            return -1;
        } else if (this.year > o.year) {
            return 1;
        } else {
            if (this.month < o.month) {
                return -1;
            } else if (this.month > o.month) {
                return 1;
            } else {
                if (this.day < o.day) {
                    return -1;
                } else if (this.day > o.day) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
