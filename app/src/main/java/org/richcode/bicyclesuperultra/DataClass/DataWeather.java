package org.richcode.bicyclesuperultra.DataClass;

public class DataWeather {

    String lockor;
    String loceng;

    public DataWeather(String lockor, String loceng) {
        this.lockor = lockor;
        this.loceng = loceng;
    }

    public String getLockor() {
        return lockor;
    }

    public void setLockor(String lockor) {
        this.lockor = lockor;
    }

    public String getLoceng() {
        return loceng;
    }

    public void setLoceng(String loceng) {
        this.loceng = loceng;
    }
}
