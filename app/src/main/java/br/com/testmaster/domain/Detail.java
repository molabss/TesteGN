package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 04/10/2016.
 */

public  abstract class Detail {

    private String distance;
    private Integer lead_price;
    private String title;
    private Embedded _embedded;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getLead_price() {
        return lead_price;
    }

    public void setLead_price(Integer lead_price) {
        this.lead_price = lead_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }
}
