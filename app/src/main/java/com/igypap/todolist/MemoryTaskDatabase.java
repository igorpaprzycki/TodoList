package com.igypap.todolist;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by igypap on 18.12.16.
 */

public class MemoryTaskDatabase implements ITaskDatabase {
    private static List<TodoTask> mTasks = new LinkedList<>();

    @Override
    public List<TodoTask> getTasks() {
        return Collections.unmodifiableList(mTasks);
    }

    @Override
    public void addTask(TodoTask task) {
        mTasks.add(task);
    }
}
