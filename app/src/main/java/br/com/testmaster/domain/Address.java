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

public class Address implements Parcelable {

    public Address() {

    }

    public Address(String city, String street, String neighborhood, String uf) {
        this.city = city;
        this.street = street;
        this.neighborhood = neighborhood;
        this.uf = uf;
    }

    private String city;
    private String street;
    private String neighborhood;
    private String uf;
    private Geolocation geolocation;

    //----------------------------------------------------------------------------------------------
    protected Address(Parcel in) {
        city = in.readString();
        street = in.readString();
        neighborhood = in.readString();
        uf = in.readString();
        geolocation = in.readParcelable(Geolocation.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(street);
        dest.writeString(neighborhood);
        dest.writeString(uf);
        dest.writeParcelable(geolocation, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
    //----------------------------------------------------------------------------------------------

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
