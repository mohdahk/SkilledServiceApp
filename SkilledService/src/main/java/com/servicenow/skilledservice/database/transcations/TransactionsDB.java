package com.servicenow.skilledservice.database.transcations;

import android.content.Context;

import com.servicenow.skilledservice.model.WorkRequest;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WorkRequest.class}, version = 1 , exportSchema = false)
public abstract  class TransactionsDB extends RoomDatabase {
    private static final String DATABASE_NAME = "transactions.db";
    private static TransactionsDB instance;

    public abstract TransactionsDAO transactionsDAO();

    public static synchronized TransactionsDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TransactionsDB.class, DATABASE_NAME)
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
