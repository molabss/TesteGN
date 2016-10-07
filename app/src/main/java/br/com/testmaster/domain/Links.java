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

public class Links implements Parcelable{


    private Self self;
    private Accept accept;
    private Reject reject;

    //------------------------------------------------------------------------------------------
    protected Links(Parcel in) {
        self = in.readParcelable(Self.class.getClassLoader());
        accept = in.readParcelable(Accept.class.getClassLoader());
        reject = in.readParcelable(Reject.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(self, flags);
        dest.writeParcelable(accept, flags);
        dest.writeParcelable(reject, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
    //------------------------------------------------------------------------------------------

    public Accept getAccept() {
        return accept;
    }

    public void setAccept(Accept accept) {
        this.accept = accept;
    }

    public Reject getReject() {
        return reject;
    }

    public void setReject(Reject reject) {
        this.reject = reject;
    }

    public Links() {
    }

    public Links(Self self) {
        this.self = self;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Links{" +
                "self=" + self +
                ", accept=" + accept +
                ", reject=" + reject +
                '}';
    }
}
