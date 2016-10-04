package br.com.testmaster.domain;

import java.util.List;

/**
 * Created by Moisés Santana on 03/10/2016.
 */

public class OfferWrapper {

    private List<Offer> offers;
    private Links _links;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
