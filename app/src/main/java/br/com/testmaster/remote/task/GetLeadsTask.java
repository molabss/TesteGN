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

import br.com.testmaster.domain.LeadWrapper;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.EntryPoint;
import br.com.testmaster.remote.LeadService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by casa on 09/10/2016.
 */

public class GetLeadsTask {

    Context mContext;


    public GetLeadsTask(Context mContext) {
        this.mContext = mContext;
    }

    public GetLeadsTask() {
    }

    public class Task extends AsyncTask<Void, Void, LeadWrapper>{

        public AsyncResponse delegate = null;
        LeadWrapper leadW;


        @Override
        protected LeadWrapper doInBackground(Void... voids) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EntryPoint.URl_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            try {
                LeadService service = retrofit.create(LeadService.class);
                Call<LeadWrapper> requestOffers = service.listLeads();
                Response<LeadWrapper> response = requestOffers.execute();
                leadW = response.body();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return leadW;
        }


        @Override
        protected void onPostExecute(LeadWrapper leadWrapper) {
            super.onPostExecute(leadWrapper);
            delegate.processFinish(leadWrapper);
        }

    }


}
