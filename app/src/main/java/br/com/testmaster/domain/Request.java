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

public class Request implements Parcelable{

    private String title;
    private String created_at;
    private Embedded _embedded;

    public Request() {
    }

    public Request(String title, String created_at, Embedded _embedded) {
        this.title = title;
        this.created_at = created_at;
        this._embedded = _embedded;
    }

    //------------------------------------------------------------------------------------------
    protected Request(Parcel in) {
        title = in.readString();
        created_at = in.readString();
        _embedded = in.readParcelable(Embedded.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(created_at);
        dest.writeParcelable(_embedded, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
    //------------------------------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
