package com.servicenow.skilledservice.database.transcations;

import com.servicenow.skilledservice.model.WorkRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TransactionsDAO {
    public String TRANSACTIONS_TABLE = "transaction_table";

    @Insert(onConflict = REPLACE)
    void insert(WorkRequest workRequest);

    @Query("SELECT * FROM " + TRANSACTIONS_TABLE)
    LiveData<List<WorkRequest>> getRequestData();

}
