package com.igypap.todolist.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.igypap.todolist.R;
import com.igypap.todolist.activity.TaskPreviewActivity;
import com.igypap.todolist.database.ITaskDatabase;
import com.igypap.todolist.database.SqliteTaskDatabase;
import com.igypap.todolist.model.TodoTask;


public class TodoNotificationService extends IntentService {
    private ITaskDatabase mTaskDatabase;

    public TodoNotificationService() {
        super("TodoNotificationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //only one connection to database for all lifecycle of service
        //multiple reminders in short period of time will not cause several database connections
        mTaskDatabase = new SqliteTaskDatabase(this);


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int taskId = intent.getIntExtra("id", -1);
        TodoTask task = mTaskDatabase.getTask(taskId);

        if (task == null){
            //if task dosent exist in the time that service
            // want to run reminder alarm
            return;
        }
        //configuration of notification
        Intent previewIntent = new Intent(this, TaskPreviewActivity.class);
        previewIntent.putExtra("pos", taskId);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,taskId,previewIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(task.getName())
                //.setContentInfo("Info")
                .setContentText("Przypominacz")

                .setDefaults(Notification.DEFAULT_ALL) //default options required to show
                    // notification at the top
                .setTicker(task.getNote()) //for old Android versions
                .setPriority(NotificationCompat.PRIORITY_MAX) //required to show the notification
                .setContentIntent(pendingIntent) //bind notification click with TaskPreviewActivity
                .setAutoCancel(true)//click on the notification cause notification to disappear
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(taskId,notification);



    }


}
