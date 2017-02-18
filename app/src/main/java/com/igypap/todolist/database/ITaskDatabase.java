package com.igypap.todolist.database;

import com.igypap.todolist.model.TodoTask;

import java.util.Date;
import java.util.List;

/**
 * Created by igypap on 18.12.16.
 */

public interface ITaskDatabase {
    List<TodoTask> getTasks();

    void addTask(TodoTask task);

    TodoTask getTask(int position);

    void updateTask(TodoTask task, int position);

    void deleteTask(TodoTask task);

    List<TodoTask> getFutureTasksWithReminder(Date now);
}
