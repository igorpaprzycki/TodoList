package com.igypap.todolist;

import java.util.List;

/**
 * Created by igypap on 18.12.16.
 */

public interface ITaskDatabase {
    List<TodoTask> getTasks();

    void addTask(TodoTask task);
}
