package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */

public class Request {

    private String title;
    private String created_at;
    private Embedded _embedded;

    public Request() {
    }

    public Request(String title, String created_at, Embedded _embedded) {
        this.title = title;
        this.created_at = created_at;
        this._embedded = _embedded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
