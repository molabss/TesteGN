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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.testmaster.R;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.Lead;
import br.com.testmaster.domain.LeadWrapper;

/**
 * Created by casa on 09/10/2016.
 */

public class LeadsAdapter extends RecyclerView.Adapter<LeadsAdapter.LeadsViewHolder>{

    private LeadWrapper mLeadWrp;

    private String mState;
    private Lead mLead;


    public static class LeadsViewHolder extends RecyclerView.ViewHolder {

        private TextView mItem_title;
        private TextView mUser;
        private TextView mCreated_at;
        private TextView mLocal;
        private View mUserIconView;
        private View mDataIconView;
        private View mLocalIconView;


        public LeadsViewHolder(View itemView) {
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

    public LeadsAdapter(LeadWrapper mLeadWrp) {
        this.mLeadWrp = mLeadWrp;
    }


    @Override
    public LeadsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leads_list, parent, false);
        LeadsViewHolder lvh = new LeadsViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LeadsViewHolder holder, int position) {
        mLead = mLeadWrp.getLeads().get(position);

            holder.mUserIconView.setBackgroundResource(R.mipmap.account_green);
            holder.mDataIconView.setBackgroundResource(R.mipmap.account_green);
            holder.mLocalIconView.setBackgroundResource(R.mipmap.map_green);

        Address ad = mLead.get_embedded().getAddress();
        holder.mItem_title.setText(mLead.get_embedded().getRequest().getTitle());
        holder.mUser.setText(mLead.get_embedded().getUser().getName());
        holder.mCreated_at.setText(mLead.getFrtCreated_at());
        holder.mLocal.setText(ad.getNeighborhood() + " - " + ad.getCity());
    }

    @Override
    public int getItemCount() {
        return mLeadWrp.getLeads().size();
    }

}
