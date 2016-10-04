package br.com.testmaster.domain;

import java.util.List;

/**
 * Created by Moisés Santana on 04/10/2016.
 */

public class Info {

    private String label;
    private List<String> value;

    public Info() {
    }

    public Info(String label, List<String> value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
