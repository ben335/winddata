package org.bg.winddata.domain;

import java.sql.Timestamp;

public class Reading {

    int id;
    Timestamp dateTime;
    int backed;
    int avgWindDirection;
    int veered;
    double minWindSpeed;
    double avgWindSpeed;
    double maxWindSpeed;
    int stationId; //foreign key


    public Reading() {
    }

    public Reading(int id, Timestamp dateTime, int backed, int avgWindDirection, int veered, double minWindSpeed, double avgWindSpeed, double maxWindSpeed, int stationId) {
        this.id = id;
        this.dateTime = dateTime;
        this.backed = backed;
        this.avgWindDirection = avgWindDirection;
        this.veered = veered;
        this.minWindSpeed = minWindSpeed;
        this.avgWindSpeed = avgWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.stationId = stationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getBacked() {
        return backed;
    }

    public void setBacked(int backed) {
        this.backed = backed;
    }

    public int getAvgWindDirection() {
        return avgWindDirection;
    }

    public void setAvgWindDirection(int avgWindDirection) {
        this.avgWindDirection = avgWindDirection;
    }

    public int getVeered() {
        return veered;
    }

    public void setVeered(int veered) {
        this.veered = veered;
    }

    public double getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(double minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}
