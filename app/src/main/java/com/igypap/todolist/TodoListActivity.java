package com.igypap.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity
        implements TodoTaskAdapter.OnClickListener {
    @BindView(R.id.tasks_list)
    RecyclerView mTodoList;

    private ITaskDatabase mTaskDatabase;
    private TodoTaskAdapter mAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        refreshListData();
    }

    private void refreshListData() {
        mAdapter.setTasks(mTaskDatabase.getTasks());
    }

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
        mTaskDatabase = new SqliteTaskDatabase(this);

        //1. Layout of to do list elements (linear vertical)
        //1 element per row
        mTodoList.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TodoTaskAdapter(mTaskDatabase.getTasks(), this);
        mTodoList.setAdapter(mAdapter);

    }

    @Override
    public void onClick(TodoTask task, int position) {
        Intent createTaskIntent = new Intent(this,TaskCreateActivity.class);
        createTaskIntent.putExtra("pos",position);
        startActivity(createTaskIntent);
        //Toast.makeText(this, "Klik" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskDoneChanged(TodoTask task, int position, boolean isDone) {
        task.setDone(isDone);
        task.setDateCreated(new Date());
        mTaskDatabase.updateTask(task,position);
        refreshListData();
    }


}
