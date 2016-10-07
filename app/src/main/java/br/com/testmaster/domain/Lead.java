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

public class Lead implements Parcelable{

    private String created_at;
    private Embedded _embedded;
    private Links _links;

    public Lead() {
    }

    public Lead(String created_at, Embedded _embedded, Links _links) {
        this.created_at = created_at;
        this._embedded = _embedded;
        this._links = _links;
    }

    //----------------------------------------------------------------------------------------------
    protected Lead(Parcel in) {
        created_at = in.readString();
        _embedded = in.readParcelable(Embedded.class.getClassLoader());
        _links = in.readParcelable(Links.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_at);
        dest.writeParcelable(_embedded, flags);
        dest.writeParcelable(_links, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lead> CREATOR = new Creator<Lead>() {
        @Override
        public Lead createFromParcel(Parcel in) {
            return new Lead(in);
        }

        @Override
        public Lead[] newArray(int size) {
            return new Lead[size];
        }
    };
    //----------------------------------------------------------------------------------------------

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
}
