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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import br.com.testmaster.R;
import br.com.testmaster.domain.Info;
import br.com.testmaster.domain.OfferDetail;

public class OfferDetailInfoAdapter  extends RecyclerView.Adapter<OfferDetailInfoAdapter.OfferDetailInfoViewHolder> {

    private OfferDetail offerDetail;
    String state;
    List<Info> infos;

    public static class OfferDetailInfoViewHolder extends RecyclerView.ViewHolder {

        private View statusCircle;
        private TextView label;
        private TextView value;

        public OfferDetailInfoViewHolder(View itemView) {
            super(itemView);
            statusCircle = itemView.findViewById(R.id.statusCircle);
            label = (TextView) itemView.findViewById(R.id.label);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }

    public OfferDetailInfoAdapter(OfferDetail offerDetail, String state) {
        this.offerDetail = offerDetail;
        this.state = state;
    }

    /*
    *
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    * */


    @Override
    public OfferDetailInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_detail_infos,parent,false);
        OfferDetailInfoViewHolder odiVh = new OfferDetailInfoViewHolder(v);
        return odiVh;
    }


    @Override
    public void onBindViewHolder(OfferDetailInfoViewHolder holder, int position) {

        if("unread".equals(state)){
            holder.statusCircle.setBackgroundResource(R.drawable.shape_gray);
        }else{
            holder.statusCircle.setBackgroundResource(R.drawable.shape_blue);
        }

        holder.label.setText(offerDetail.get_embedded().getInfo().get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return offerDetail.get_embedded().getInfo().size();
    }

}
