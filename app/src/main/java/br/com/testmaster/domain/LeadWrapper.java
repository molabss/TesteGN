package br.com.testmaster.domain;

import java.util.List;

/**
 * Created by Moisés Santana on 03/10/2016.
 */

public class LeadWrapper {

    private List<Lead> leads;
    private Links _links;

    public LeadWrapper() {
    }

    public LeadWrapper(List<Lead> leads, Links _links) {
        this.leads = leads;
        this._links = _links;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
