package com.servicenow.skilledservice.database.workers;

import android.content.Context;

import com.servicenow.skilledservice.model.Worker;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Worker.class}, version = 1, exportSchema = false)
public abstract class WorkersDB extends RoomDatabase {

    private static final String DATABASE_NAME = "workers.db";
    private static WorkersDB instance;

    public abstract WorkersDAO workersDAO();

    public static synchronized WorkersDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, WorkersDB.class, DATABASE_NAME)
                    .addCallback(roomCallBack)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
