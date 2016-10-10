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
import br.com.testmaster.domain.LeadDetail;
import br.com.testmaster.domain.Links;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.EntryPoint;
import br.com.testmaster.remote.LeadService;
import br.com.testmaster.remote.Task;
import br.com.testmaster.util.Network;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetLeadDetailTask extends Task {

    public GetLeadDetailTask(Context mContext) {
        super(mContext);
    }

    public class Task extends AsyncTask<Links, Void, LeadDetail> {

        public AsyncResponse delegate = null;
        LeadDetail detail;

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
        protected LeadDetail doInBackground(Links... linkses) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EntryPoint.URl_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            try{
                LeadService service = retrofit.create(LeadService.class);
                Call<LeadDetail> requestDetails = service.getDetail(linkses[0].getSelf().getHref());
                Response<LeadDetail> response = requestDetails.execute();
                detail = response.body();
            }catch (Exception e){
                e.printStackTrace();
                erroOcorreu();
                setMsg(getContext().getResources().getString(R.string.errorGetLeadDetails));
            }
            return detail;
        }

        @Override
        protected void onPostExecute(LeadDetail leadDetail) {
            super.onPostExecute(leadDetail);
            if(teveAlgumErro()){
                alert();
            }
            this.delegate.processFinish(leadDetail);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            onPostExecute(null);
        }
    }
}
