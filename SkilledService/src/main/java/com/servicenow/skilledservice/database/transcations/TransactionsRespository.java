package com.servicenow.skilledservice.database.transcations;

import android.app.Application;

import com.servicenow.skilledservice.model.WorkRequest;
import com.servicenow.skilledservice.utils.AppConstants;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class TransactionsRespository {
    private final TransactionsDAO transactionsDAO;
    private final Executor executor;

    public TransactionsRespository(Application application) {
        TransactionsDB database = TransactionsDB.getInstance(application);
        transactionsDAO = database.transactionsDAO();
        executor = Executors.newSingleThreadExecutor();
    }

    public void createRequest(final String workername , final int workedId , final String specialization ){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                transactionsDAO.insert(new WorkRequest(workedId,workername, specialization, AppConstants.KEY_NEW));
            }
        });

    }

    public LiveData<List<WorkRequest>> GetRequestData(){
        return transactionsDAO.getRequestData();
    }

}
