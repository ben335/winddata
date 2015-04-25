package org.bg.winddata.domain;

public class Station {
    int id;
    String name;
    //String url;
    //gpslocation
    //maintained by


    public Station() {
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
