package br.com.testmaster.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.testmaster.R;
import br.com.testmaster.remote.AsyncResponse;


public class LeadsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AsyncResponse {

    /*
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    OfferWrapper offerWrp;
    OffersAdapter offerAdapter;
    * */


    public LeadsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRemoteData();

    }

    public void loadRemoteData(){
    /*
            GetOffersTask.Task task = new GetOffersTask().new Task();
            task.delegate = this;
            task.execute();
    * */
    }


    @Override
    public void processFinish(Object output) {
    /*
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
    * */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leads, container, false);

        /*
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        return rootView;
        * */
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
    public void onRefresh() {

    }


}
