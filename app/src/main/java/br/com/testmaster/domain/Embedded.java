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

public class Embedded implements Parcelable{

    private Address address;
    private User user;
    private Request request;
    //private Info info;
    private List<Info> info;
    private List<Phone> phones;

    public Embedded() {
    }

    public Embedded(Address address, User user, Request request, List<Info> info, List<Phone> phones) {
        this.address = address;
        this.user = user;
        this.request = request;
        this.info = info;
        this.phones = phones;
    }

    protected Embedded(Parcel in) {
        address = in.readParcelable(Address.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        request = in.readParcelable(Request.class.getClassLoader());
        info = in.createTypedArrayList(Info.CREATOR);
        phones = in.createTypedArrayList(Phone.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(address, flags);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(request, flags);
        dest.writeTypedList(info);
        dest.writeTypedList(phones);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Embedded> CREATOR = new Creator<Embedded>() {
        @Override
        public Embedded createFromParcel(Parcel in) {
            return new Embedded(in);
        }

        @Override
        public Embedded[] newArray(int size) {
            return new Embedded[size];
        }
    };

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //public Info getInfo() {
        //return info;
    //}

    //public void setInfo(Info info) {
        //this.info = info;
    //}

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
