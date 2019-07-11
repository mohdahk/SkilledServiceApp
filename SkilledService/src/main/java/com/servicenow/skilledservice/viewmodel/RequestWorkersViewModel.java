package com.servicenow.skilledservice.viewmodel;

import android.app.Application;

import com.servicenow.skilledservice.database.transcations.TransactionsRespository;
import com.servicenow.skilledservice.model.WorkRequest;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RequestWorkersViewModel extends AndroidViewModel {
    private TransactionsRespository transactionsRespository;
    public RequestWorkersViewModel(@NonNull Application application) {
        super(application);
        transactionsRespository = new TransactionsRespository(application);
    }

    public void createRequest(String workerName, int workerid, String specialization){
           transactionsRespository.createRequest(workerName , workerid , specialization);
    }
     public LiveData<List<WorkRequest>> GetRequestData(){
        return transactionsRespository.GetRequestData();
     }

}
