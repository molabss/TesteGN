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

public class Offer implements Parcelable{

    private String state;
    private Embedded _embedded;
    private Links _links;


    public Offer() {
    }

    public Offer(String state, Embedded _embedded, Links _links) {
        this.state = state;
        this._embedded = _embedded;
        this._links = _links;
    }

    //--------------------------------------------------------------------------------------------
    protected Offer(Parcel in) {
        state = in.readString();
        _embedded = in.readParcelable(Embedded.class.getClassLoader());
        _links = in.readParcelable(Links.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeParcelable(_embedded, flags);
        dest.writeParcelable(_links, flags);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };
    //--------------------------------------------------------------------------------------------

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "state='" + state + '\'' +
                ", _embedded=" + _embedded +
                ", _links=" + _links +
                '}';
    }
}
