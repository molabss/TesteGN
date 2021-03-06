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

import java.util.ArrayList;
import java.util.List;

public class OfferWrapper implements Parcelable{

    private List<Offer> offers;
    private Links _links;

    public OfferWrapper() {
        offers = new ArrayList<Offer>();
    }

    //--------------------------------------------------------------------------------------------
    protected OfferWrapper(Parcel in) {
        offers = in.createTypedArrayList(Offer.CREATOR);
        _links = in.readParcelable(Links.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(offers);
        dest.writeParcelable(_links, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OfferWrapper> CREATOR = new Creator<OfferWrapper>() {
        @Override
        public OfferWrapper createFromParcel(Parcel in) {
            return new OfferWrapper(in);
        }

        @Override
        public OfferWrapper[] newArray(int size) {
            return new OfferWrapper[size];
        }
    };
    //--------------------------------------------------------------------------------------------

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
