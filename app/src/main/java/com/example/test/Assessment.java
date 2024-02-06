package com.example.test;

public class Assessment extends Event {
    private String testTitle;
    private String building_AND_room;
    private int startHour;
    private int startMinute;
    private boolean isComplete;

    /**
     * @param year year of event
     * @param month month of event 1-12
     * @param day day of event 1-31
     * @param courseName name of course
     * @param testTitle the title of the test
     * @param building_AND_room building and room the class is in
     * @param startHour start time hour 0-23
     * @param startMinute end time 0-59
     */
    public Assessment(int year, int month, int day, String courseName, String testTitle,
                      String building_AND_room, int startHour, int startMinute) {
        super(year, month, day, courseName);
        this.testTitle = testTitle;
        this.building_AND_room = building_AND_room;
        this.startHour = startHour;
        this.startMinute = startMinute;
        isComplete = false;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String toString() {
        return super.toString() + "," + testTitle + "," + building_AND_room + "," + startHour
                + "," + startMinute + "," + isComplete;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass().equals(obj.getClass())) {
            Assessment cp = (Assessment) obj;
            return super.equals(obj) && this.testTitle.equals(cp.testTitle) &&
                    this.building_AND_room.equals(cp.building_AND_room) &&
                    this.startHour == startHour && this.startMinute == startMinute;
        } else {
            return false;
        }
    }

    public int compareTo(Assessment o) {
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
