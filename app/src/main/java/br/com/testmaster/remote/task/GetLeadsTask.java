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

import br.com.testmaster.R;
import br.com.testmaster.domain.LeadWrapper;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.EntryPoint;
import br.com.testmaster.remote.LeadService;
import br.com.testmaster.remote.Task;
import br.com.testmaster.util.Network;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by casa on 09/10/2016.
 */

public class GetLeadsTask extends Task {


    public GetLeadsTask(Context mContext) {
        super(mContext);
    }



    public class Task extends AsyncTask<Void, Void, LeadWrapper>{

        public AsyncResponse delegate = null;
        LeadWrapper leadW;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            limpaErroHist();
            clearMsg();
            if(Network.isNetworkUnavailable(getContext())){
                setMsg(getContext().getResources().getString(R.string.errorInternetConnect));
                erroOcorreu();
                cancel(true);
            }
        }

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
                erroOcorreu();
                setMsg(getContext().getResources().getString(R.string.errorGetLeads));
            }
            return leadW;
        }


        @Override
        protected void onPostExecute(LeadWrapper leadWrapper) {
            super.onPostExecute(leadWrapper);
            if(teveAlgumErro()){
                alert();
            }
            delegate.processFinish(leadWrapper);
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            onPostExecute(null);
        }

    }


}
