package com.igypap.todolist.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.igypap.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by igypap on 08.01.17.
 */

public class TaskPreviewActivity extends TaskCreateActivity {

    @BindView(R.id.btn_save)
    Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mTaskTitle.setEnabled(false);
        mTaskNote.setEnabled(false);

        mTaskReminder.setVisibility(View.GONE);
        mTaskReminderDate.setVisibility(View.GONE);
        mTaskReminderTime.setVisibility(View.GONE);
        mSaveButton.setVisibility(View.GONE);
    }
}
