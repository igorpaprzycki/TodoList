package com.igypap.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity {
    @BindView(R.id.tasks_list)
    RecyclerView mTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);

        //1. Layout of to do ist elements (linear vertical)
        mTodoList.setLayoutManager(new LinearLayoutManager(this));

        List<TodoTask> tasks = new LinkedList<>();
        TodoTask task = new TodoTask();
        task.setName("Zadanie 1");
        tasks.add(task);

        task = new TodoTask();
        task.setDone(true);
        task.setName("Zadanie 1");
        tasks.add(task);

        TodoTaskAdapter adapter = new TodoTaskAdapter(tasks);
        mTodoList.setAdapter(adapter);
        //mTodoList.setAdapter(listAdapter);
    }
}
