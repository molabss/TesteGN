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

public class Reject implements Parcelable{

    private String href;

    protected Reject(Parcel in) {
        href = in.readString();
    }

    //--------------------------------------------------------------------------------------------
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reject> CREATOR = new Creator<Reject>() {
        @Override
        public Reject createFromParcel(Parcel in) {
            return new Reject(in);
        }

        @Override
        public Reject[] newArray(int size) {
            return new Reject[size];
        }
    };
    //--------------------------------------------------------------------------------------------

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
