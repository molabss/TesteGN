package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 04/10/2016.
 */

public class Offer {

    private String state;
    private Embedded _embedded;
    private Links _links;


    public Offer() {
    }

    public Offer(String state, Embedded _embedded, Links _links) {
        this.state = state;
        this._embedded = _embedded;
        this._links = _links;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
