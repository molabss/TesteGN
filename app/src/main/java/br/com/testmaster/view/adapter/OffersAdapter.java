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

import br.com.testmaster.R;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.Offer;
import br.com.testmaster.domain.OfferWrapper;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    private OfferWrapper mOfferWrp;

    private String mState;
    private Offer mOffer;

    public static class OffersViewHolder extends RecyclerView.ViewHolder {

        private TextView mItem_title;
        private TextView mUser;
        private TextView mCreated_at;
        private TextView mLocal;
        private View mUserIconView;
        private View mDataIconView;
        private View mLocalIconView;


        public OffersViewHolder(View itemView) {
            super(itemView);
            mItem_title = (TextView) itemView.findViewById(R.id.item_title);
            mUser = (TextView) itemView.findViewById(R.id.user);
            mCreated_at = (TextView) itemView.findViewById(R.id.created_at);
            mLocal = (TextView) itemView.findViewById(R.id.local);
            mUserIconView = itemView.findViewById(R.id.userIconView);
            mDataIconView = itemView.findViewById(R.id.dataIconView);
            mLocalIconView = itemView.findViewById(R.id.localIconView);

        }
    }

    public OffersAdapter(OfferWrapper mOfferWrp) {
        this.mOfferWrp = mOfferWrp;
    }

    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_list, parent, false);
        OffersViewHolder ovh = new OffersViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {

        mOffer = mOfferWrp.getOffers().get(position);
        mState = mOfferWrp.getOffers().get(position).getState();

        if("unread".equals(mState)){
            holder.mUserIconView.setBackgroundResource(R.mipmap.account_blue);
            holder.mDataIconView.setBackgroundResource(R.mipmap.account_blue);
            holder.mLocalIconView.setBackgroundResource(R.mipmap.map_blue);
        }else{
            holder.mUserIconView.setBackgroundResource(R.mipmap.account_gray);
            holder.mDataIconView.setBackgroundResource(R.mipmap.account_gray);
            holder.mLocalIconView.setBackgroundResource(R.mipmap.map_gray);
        }
        Address ad = mOffer.get_embedded().getRequest().get_embedded().getAddress();
        holder.mItem_title.setText(mOffer.get_embedded().getRequest().getTitle());
        holder.mUser.setText(mOffer.get_embedded().getRequest().get_embedded().getUser().getName());
        holder.mCreated_at.setText(mOffer.get_embedded().getRequest().getFrtCreated_at());
        holder.mLocal.setText(ad.getNeighborhood() + " - " + ad.getCity());
    }

    @Override
    public int getItemCount() {
        return mOfferWrp.getOffers().size();
    }
}