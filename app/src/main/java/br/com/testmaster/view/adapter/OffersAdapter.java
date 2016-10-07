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

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.testmaster.R;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.OfferWrapper;

/**
 *
 */
public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    private OfferWrapper mOfferWrp;

    public static class OffersViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView item_title;
        private TextView txtContr;
        private TextView data;
        private TextView local;

        public OffersViewHolder(View itemView) {
            super(itemView);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            txtContr = (TextView) itemView.findViewById(R.id.txtContr);
            data = (TextView) itemView.findViewById(R.id.data);
            local = (TextView) itemView.findViewById(R.id.local);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Click detected on item " + getAdapterPosition(),
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();




                }
            });
        }
    }

    public OffersAdapter(OfferWrapper mOfferWrp) {
        this.mOfferWrp = mOfferWrp;
    }

    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        OffersViewHolder ovh = new OffersViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {
        Address ad = mOfferWrp.getOffers().get(position).get_embedded().getRequest().get_embedded().getAddress();
        holder.item_title.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().getTitle());
        holder.txtContr.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().get_embedded().getUser().getName());
        holder.data.setText(mOfferWrp.getOffers().get(position).get_embedded().getRequest().getCreated_at());
        holder.local.setText(ad.getNeighborhood() + " - " + ad.getCity());
    }

    @Override
    public int getItemCount() {
        return mOfferWrp.getOffers().size();
    }
}