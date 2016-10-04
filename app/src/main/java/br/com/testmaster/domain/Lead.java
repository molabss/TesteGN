package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */

public class Lead {

    private String created_at;
    private Embedded _embedded;
    private Links _links;

    public Lead() {
    }

    public Lead(String created_at, Embedded _embedded, Links _links) {
        this.created_at = created_at;
        this._embedded = _embedded;
        this._links = _links;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
