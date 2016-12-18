package com.igypap.todolist;

import java.util.Date;

/**
 * Created by igypap on 18.12.16.
 */

public class TodoTask {
    private String name;
    private String note;
    private boolean done;
    private Date dateCreated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
