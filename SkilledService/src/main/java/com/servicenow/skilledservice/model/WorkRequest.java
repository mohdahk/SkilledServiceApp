package com.servicenow.skilledservice.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.servicenow.skilledservice.database.transcations.TransactionsDAO.TRANSACTIONS_TABLE;

@Entity(tableName = TRANSACTIONS_TABLE)
public class WorkRequest {

    public WorkRequest(){}

    public WorkRequest(int workerId , String workerName, String specialization , String status){
        this.workerId = workerId;
        this.workername = workerName;
        this.specialization = specialization;
        this.status = status;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int workerId;
    private String workername;
    private String specialization;
    private String status;


    public int getId() {
        return id;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getWorkername() {
        return workername;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getStatus() {
        return status;
    }

}
