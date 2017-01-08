package com.igypap.todolist.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.igypap.todolist.NotificationsPlanner;
import com.igypap.todolist.database.ITaskDatabase;
import com.igypap.todolist.database.SqliteTaskDatabase;

/**
 * Created by igypap on 08.01.17.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ITaskDatabase taskDatabase = new SqliteTaskDatabase(context);

        new NotificationsPlanner(taskDatabase,context).planNotifications();
    }
}
