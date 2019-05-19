package org.richcode.bicyclesuperultra.DataClass;


public class DataRecord {

    int id;
    String date;
    String kg;
    String kcal;
    String time;
    String distance;

    public DataRecord(int id, String date, String kg, String kcal, String time, String distance) {
        this.id = id;
        this.date = date;
        this.kg = kg;
        this.kcal = kcal;
        this.time = time;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
