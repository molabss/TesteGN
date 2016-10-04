package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */
public class Embedded {

    private Address address;
    private User user;
    private Request request;

    public Embedded() {
    }

    public Embedded(Address address, User user, Request request) {
        this.address = address;
        this.user = user;
        this.request = request;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
