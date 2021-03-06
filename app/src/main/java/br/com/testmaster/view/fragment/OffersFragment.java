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

package br.com.testmaster.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.com.testmaster.R;
import br.com.testmaster.domain.OfferWrapper;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.task.GetOffersTask;
import br.com.testmaster.view.activity.OfferDetailActivity;
import br.com.testmaster.view.listener.RecyclerItemClickListener;
import br.com.testmaster.view.adapter.OffersAdapter;

/**
 *
 */
public class OffersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AsyncResponse {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    OfferWrapper offerWrp;
    OffersAdapter offerAdapter;

    public OffersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRemoteData();
    }

    /**
     *
     */
    public void loadRemoteData(){
        GetOffersTask.Task task = new GetOffersTask(getActivity()).new Task();
        task.delegate = this;
        task.execute();
    }

    /**
     *
     */
    @Override
    public void processFinish(Object output) {

        LinearLayout emptyLay = (LinearLayout)getView().findViewById(R.id.emptyMessageLay);

        if(output == null){
            emptyLay.setVisibility(View.VISIBLE);
            return;
        }else{
            emptyLay.setVisibility(View.GONE);
        }

        offerWrp = (OfferWrapper) output;
        offerAdapter = new OffersAdapter(offerWrp);
        mRecyclerView.setAdapter(offerAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setRefreshing(false);

        mRecyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(new Intent(getActivity(),OfferDetailActivity.class)
                            .putExtra("offer",offerWrp.getOffers().get(position))
                            .putExtra("state",offerWrp.getOffers().get(position).getState()));
                }
            })
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        loadRemoteData();
    }
}