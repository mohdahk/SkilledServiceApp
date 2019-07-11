package com.servicenow.skilledservice.viewmodel;

import android.app.Application;

import com.servicenow.skilledservice.database.workers.WorkersRepository;
import com.servicenow.skilledservice.model.Worker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SearchWorkersViewModel extends AndroidViewModel {
    private WorkersRepository workersRepository;
    public SearchWorkersViewModel(@NonNull Application application) {
        super(application);
         workersRepository = new WorkersRepository(application);
    }
    public LiveData<List<Worker>> getWorkersData(){
        return workersRepository.getWorkersData();
    }
    public LiveData<List<Worker>> searchWorkersData(String queryString){
        return workersRepository.searchWorkersData(queryString);
    }
}
