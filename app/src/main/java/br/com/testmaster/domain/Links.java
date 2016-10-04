package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */
public class Links {


    private Self self;

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
