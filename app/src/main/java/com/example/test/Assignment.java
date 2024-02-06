package com.example.test;

public class Assignment extends Event {
    private String assignmentTitle;
    private boolean isComplete;

    /**
     * @param year year of event
     * @param month month of event 1-12
     * @param day day of event 1-31
     * @param courseName name of course
     * @param assignmentTitle the title of the assignment
     */
    public Assignment(int year, int month, int day, String courseName, String assignmentTitle) {
        super(year, month, day, courseName);
        this.assignmentTitle = assignmentTitle;
        isComplete = false;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String toString() {
        return super.toString() + "," + assignmentTitle + "," + isComplete;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass().equals(obj.getClass())) {
            Assignment cp = (Assignment) obj;
            return super.equals(obj) && this.assignmentTitle.equals(cp.assignmentTitle);
        } else {
            return false;
        }
    }
}