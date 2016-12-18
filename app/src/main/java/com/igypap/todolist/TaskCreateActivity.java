package com.igypap.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskCreateActivity extends AppCompatActivity {
    @BindView(R.id.task_title)
    EditText mTaskTitle;
    @BindView(R.id.task_note)
    EditText mTaskNote;
    private ITaskDatabase mTaskDatabase = new MemoryTaskDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save)
    void onSaveClick() {
        TodoTask task = new TodoTask();
        task.setDateCreated(new Date());
        task.setName(mTaskTitle.getText().toString());
        task.setNote(mTaskNote.getText().toString());

        mTaskDatabase.addTask(task);

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
