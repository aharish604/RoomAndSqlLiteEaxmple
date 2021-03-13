package com.example.appcaretask.RoomDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.appcaretask.Bean.ProfileBean;

@Database(version = 1,entities = {ProfileBean.class})
public abstract class TaskAppDatabase extends RoomDatabase {

public abstract TaskDao taskDao();

    private static TaskAppDatabase noteDB;

    public static TaskAppDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }
    private static TaskAppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                TaskAppDatabase.class, "Room_AppcareDatabase")
                .allowMainThreadQueries().build();
    }

   /* public void cleanUp(){
        noteDB = null;
    }*/


}
