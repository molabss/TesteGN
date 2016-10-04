package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 04/10/2016.
 */
public class Geolocation {

    private String latitude;
    private String longitude;

    public Geolocation() {
    }

    public Geolocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
