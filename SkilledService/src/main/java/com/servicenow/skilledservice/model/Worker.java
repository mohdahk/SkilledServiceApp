package com.servicenow.skilledservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.servicenow.skilledservice.database.workers.WorkersDAO.WORKERS_TABLE;

@Entity(tableName = WORKERS_TABLE)
public class Worker implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;
    private String specialization;
    private long ratings;

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setRatings(long ratings) {
        this.ratings = ratings;
    }

    public Worker(int id , String name, String specialization , long rating){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.ratings = rating;
    }

    protected Worker(Parcel in){
        id = in.readInt();
        name = in.readString();
        specialization = in.readString();
        ratings = in.readLong();
    }

    public  Worker(){
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public long getRatings() {
        return ratings;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(specialization);
        dest.writeLong(ratings);

    }
}
