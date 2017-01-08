package com.igypap.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.igypap.todolist.model.TodoTask;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by igypap on 07.01.17.
 */

public class TodoDbOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "todo.DB";
    public static final int DATABASE_VERSION = 2;

    public TodoDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,TodoTask.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //change the database from version 1 to version 2 with additional columns for reminder date
        if (oldVersion == 1 && newVersion == 2){
            database.execSQL("ALTER TABLE todo_task ADD COLUMN reminder BOOLEAN NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE todo_task ADD COLUMN reminderDate TEXT");

        }
    }
}
