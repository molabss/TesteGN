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

package br.com.testmaster.remote.task;

import android.content.Context;
import android.os.AsyncTask;

import br.com.testmaster.domain.OfferWrapper;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.EntryPoint;
import br.com.testmaster.remote.OfferService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Params Progress Result

public class GetOffersTask {

    Context mContext;

    public GetOffersTask(Context mContext) {
        this.mContext = mContext;
    }

    public GetOffersTask() {
    }

    public class Task extends AsyncTask<Void, Void, OfferWrapper> {

        public AsyncResponse delegate = null;
        OfferWrapper offerW;

        @Override
        protected OfferWrapper doInBackground(Void... entryPoints) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EntryPoint.URl_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            try {
                OfferService service = retrofit.create(OfferService.class);
                Call<OfferWrapper> requestOffers = service.listOffers();
                Response<OfferWrapper> response = requestOffers.execute();
                offerW = response.body();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return offerW;
        }
        @Override
        protected void onPostExecute(OfferWrapper offerWrapper) {
            super.onPostExecute(offerWrapper);
            delegate.processFinish(offerWrapper);
        }
    }
}
