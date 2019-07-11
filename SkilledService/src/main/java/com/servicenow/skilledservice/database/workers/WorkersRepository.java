package com.servicenow.skilledservice.database.workers;

import android.app.Application;

import com.servicenow.skilledservice.model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class WorkersRepository {
    private final WorkersDAO workersDAO;
    private final Executor executor;

    public WorkersRepository(Application application) {
        WorkersDB database = WorkersDB.getInstance(application);
        workersDAO = database.workersDAO();

        // Executor to run the service request in background thread
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Worker>> getWorkersData() {
        loadDataToWorkersDB();
        return workersDAO.getWorkersData();
    }

    public LiveData<List<Worker>> searchWorkersData(String queryString) {
        return workersDAO.searchWorkersData(queryString);
    }

    private void loadDataToWorkersDB(){

        executor.execute(new Runnable() {
            @Override
            public void run() {

                   workersDAO.insert(getSampleWorkersData());
            }
        });
    }

    private List<Worker> getSampleWorkersData(){
        List<Worker> workers = new ArrayList<Worker>();
        workers.add(new Worker(1,"James Butt","Carpenter".toLowerCase(),4));
        workers.add(new Worker(2,"Art Venere","Carpenter".toLowerCase(),2));
        workers.add(new Worker(3,"Donette Foller","Plumber".toLowerCase(),1));
        workers.add(new Worker(4,"Leota Dilliard","Plumber".toLowerCase(),3));
        workers.add(new Worker(5,"Lois Walker","Gardener".toLowerCase(),5));
        workers.add(new Worker(6,"Patrick Baker","Gardener".toLowerCase(),2));
        workers.add(new Worker(7,"Melissa king","Cleaner".toLowerCase(),4));
        workers.add(new Worker(8,"Paula Diaz","Cleaner".toLowerCase(),1));
        workers.add(new Worker(9,"Joshua Stewart","Cleaner".toLowerCase(),3));
        workers.add(new Worker(11,"Thomas Lewis","Driver".toLowerCase(),3));
        workers.add(new Worker(12,"Matthew Turner","Driver".toLowerCase(),1));
        workers.add(new Worker(13,"Matthew Turner","Driver".toLowerCase(),3));
        workers.add(new Worker(14,"Anne Russell","Plumber".toLowerCase(),2));
        workers.add(new Worker(15,"Donna Brown","Plumber".toLowerCase(),3));
        workers.add(new Worker(16,"Mary Bryant","Plumber".toLowerCase(),4));
        workers.add(new Worker(17,"Ruby Rogers","Plumber".toLowerCase(),1));
        workers.add(new Worker(18,"Nancy Howard","Carpenter".toLowerCase(),2));
        workers.add(new Worker(19,"Alan Rivera","Carpenter".toLowerCase(),3));
        workers.add(new Worker(20,"Henry Jenkins","Carpenter".toLowerCase(),4));
        workers.add(new Worker(21,"Cynthia Ramirez","Cleaner".toLowerCase(),5));
        workers.add(new Worker(22,"Douglas Flores","Cleaner".toLowerCase(),3));
        workers.add(new Worker(23,"Carolyn Price","Cleaner".toLowerCase(),4));
        workers.add(new Worker(24,"Lillian Mitchell","Gardener".toLowerCase(),5));
        return workers;
    }
}
