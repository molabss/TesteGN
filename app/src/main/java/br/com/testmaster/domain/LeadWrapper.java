/*
 * Copyright (c) 2016 by Moisés Santana.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package br.com.testmaster.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LeadWrapper implements Parcelable{

    private List<Lead> leads;
    private Links _links;

    public LeadWrapper() {
    }

    public LeadWrapper(List<Lead> leads, Links _links) {
        this.leads = leads;
        this._links = _links;
    }

    //------------------------------------------------------------------------------------
    protected LeadWrapper(Parcel in) {
        leads = in.createTypedArrayList(Lead.CREATOR);
        _links = in.readParcelable(Links.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(leads);
        dest.writeParcelable(_links, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LeadWrapper> CREATOR = new Creator<LeadWrapper>() {
        @Override
        public LeadWrapper createFromParcel(Parcel in) {
            return new LeadWrapper(in);
        }

        @Override
        public LeadWrapper[] newArray(int size) {
            return new LeadWrapper[size];
        }
    };
    //------------------------------------------------------------------------------------

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
