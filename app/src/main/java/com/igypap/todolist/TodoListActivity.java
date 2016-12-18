package com.igypap.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity {
    @BindView(R.id.tasks_list)
    RecyclerView mTodoList;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_create) {
            Intent createTaskIntent = new Intent(this, TaskCreateActivity.class);
            startActivity(createTaskIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todolist, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
