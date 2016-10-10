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

package br.com.testmaster.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.testmaster.R;
import br.com.testmaster.domain.Info;
import br.com.testmaster.domain.LeadDetail;

/**
 * Created by casa on 09/10/2016.
 */
public class LeadDetailInfoAdapter extends RecyclerView.Adapter<LeadDetailInfoAdapter.LeadDetailInfoViewHolder>{

    private LeadDetail leadDetail;
    String state;
    List<Info> infos;


    public static class LeadDetailInfoViewHolder extends RecyclerView.ViewHolder {

        private View statusCircle;
        private TextView label;
        private TextView value;

        public LeadDetailInfoViewHolder(View itemView) {
            super(itemView);
            statusCircle = itemView.findViewById(R.id.statusCircle);
            label = (TextView) itemView.findViewById(R.id.label);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }


    public LeadDetailInfoAdapter(LeadDetail offerDetail) {
        this.leadDetail = offerDetail;
    }

    @Override
    public LeadDetailInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_detail_infos,parent,false);
        LeadDetailInfoViewHolder ldiVh = new LeadDetailInfoViewHolder(v);
        return ldiVh;
    }

    @Override
    public void onBindViewHolder(LeadDetailInfoViewHolder holder, int position) {
        holder.statusCircle.setBackgroundResource(R.mipmap.info_green);
        holder.label.setText(leadDetail.get_embedded().getInfo().get(position).getLabel());

        //Não é possível deserializar o atributo value do json, pois ele é ao mesmo tempo array e string
        holder.value.setText(R.string.nd);

    }

    @Override
    public int getItemCount() {
        return leadDetail.get_embedded().getInfo().size();
    }
}
