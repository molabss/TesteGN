package br.com.testmaster.domain;

/**
 * Created by casa on 03/10/2016.
 */
public class Address {

    public Address() {

    }

    public Address(String city, String street, String neighborhood, String uf) {
        this.city = city;
        this.street = street;
        this.neighborhood = neighborhood;
        this.uf = uf;
    }

    private String city;
    private String street;
    private String neighborhood;
    private String uf;
    private Geolocation geolocation;

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
