package com.servicenow.skilledservice.database.workers;

import com.servicenow.skilledservice.model.Worker;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WorkersDAO {
    public String WORKERS_TABLE = "workers_table";

    @Insert(onConflict = REPLACE)
    void insert(List<Worker> albums);

    @Query("SELECT * FROM " + WORKERS_TABLE + " ORDER BY ratings DESC")
    LiveData<List<Worker>> getWorkersData();

    @Query("SELECT * FROM " + WORKERS_TABLE + " where specialization Like '%' || :queryString || '%' ORDER BY ratings")
    LiveData<List<Worker>> searchWorkersData(String queryString);
}
