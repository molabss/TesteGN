package br.com.testmaster.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.testmaster.R;
import br.com.testmaster.domain.LeadWrapper;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.task.GetLeadsTask;
import br.com.testmaster.view.activity.LeadDetailActivity;
import br.com.testmaster.view.adapter.LeadsAdapter;
import br.com.testmaster.view.listener.RecyclerItemClickListener;


public class LeadsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AsyncResponse {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LeadWrapper mLeadWrp;
    LeadsAdapter mLeadAdapter;

    public LeadsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRemoteData();

    }

    public void loadRemoteData(){
            GetLeadsTask.Task task = new GetLeadsTask().new Task();
            task.delegate = this;
            task.execute();
    }

    @Override
    public void processFinish(Object output) {
            mLeadWrp = (LeadWrapper) output;
            mLeadAdapter = new LeadsAdapter(mLeadWrp);
            mRecyclerView.setAdapter(mLeadAdapter);
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mSwipeRefreshLayout.setRefreshing(false);

            mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startActivity(new Intent(getActivity(),LeadDetailActivity.class)
                                .putExtra("lead",mLeadWrp.getLeads().get(position)));
                    }
                })
            );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leads, container, false);
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
    public void onRefresh() {

    }


}
