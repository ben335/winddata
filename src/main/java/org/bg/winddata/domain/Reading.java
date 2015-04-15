package org.bg.winddata.domain;

import java.time.LocalDateTime;

public class Reading {

    LocalDateTime dateTime;
    int backed;
    int avgWindDirection;
    int veered;
    double minWindSpeed;
    double avgWindSpeed;
    double maxWindSpeed;

    public Reading() {
    }

    public Reading(LocalDateTime dateTime, LocalDateTime time, int backed, int avgWindDirection, int veered, double minWindSpeed, double avgWindSpeed, double maxWindSpeed) {
        this.dateTime = dateTime;
        this.backed = backed;
        this.avgWindDirection = avgWindDirection;
        this.veered = veered;
        this.minWindSpeed = minWindSpeed;
        this.avgWindSpeed = avgWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
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
}
