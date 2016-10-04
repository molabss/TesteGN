package br.com.testmaster.domain;

/**
 * Created by Moisés Santana on 03/10/2016.
 */
public class User {


    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }



    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
