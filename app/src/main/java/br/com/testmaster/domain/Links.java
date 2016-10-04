package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */
public class Links {


    private Self self;

    private Accept accept;
    private Reject reject;

    public Accept getAccept() {
        return accept;
    }

    public void setAccept(Accept accept) {
        this.accept = accept;
    }

    public Reject getReject() {
        return reject;
    }

    public void setReject(Reject reject) {
        this.reject = reject;
    }

    public Links() {
    }

    public Links(Self self) {
        this.self = self;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }
}
