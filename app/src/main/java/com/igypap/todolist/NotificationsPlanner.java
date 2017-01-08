package com.igypap.todolist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.igypap.todolist.database.ITaskDatabase;
import com.igypap.todolist.model.TodoTask;
import com.igypap.todolist.service.TodoNotificationService;

import java.util.Date;
import java.util.List;

/**
 * Created by igypap on 08.01.17.
 */

public class NotificationsPlanner {
    private ITaskDatabase mTaskDatabase;
    private Context mContext;

    public NotificationsPlanner(ITaskDatabase mTaskDatabase, Context context) {
        this.mTaskDatabase = mTaskDatabase;
        this.mContext = context;
    }

    public void planNotifications() {
        //1. Get task with reminders set to on - only with future time and date
        List<TodoTask> tasks = mTaskDatabase.getFutureTasksWithReminder(new Date());

        //2. For this task plan to notifications with AlarmManager component
        //TodoNotificationService - service to run
        AlarmManager alarmManager =
                (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        for (TodoTask task : tasks) {
            Intent serviceIntent = new Intent(mContext, TodoNotificationService.class);
            serviceIntent.putExtra("id", task.getId());
            PendingIntent pendingIntent = PendingIntent.getService(
                    mContext,
                    task.getId(),
                    serviceIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
                //for android systems below API 19 set method works the same as setExact
                //setExact is available since API 19
            }
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    task.getReminderDate().getTime(),
                    pendingIntent);
        }


    }
}
