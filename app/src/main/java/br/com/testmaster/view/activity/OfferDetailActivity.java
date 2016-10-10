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

package br.com.testmaster.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import br.com.testmaster.R;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.Geolocation;
import br.com.testmaster.domain.Offer;
import br.com.testmaster.domain.OfferDetail;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.task.GetOfferDetailTask;
import br.com.testmaster.view.adapter.OfferDetailInfoAdapter;

public class OfferDetailActivity extends AppCompatActivity implements OnMapReadyCallback, AsyncResponse {

    private MapView mMapView;
    private GoogleMap mMMap;
    private Offer mOffer;
    private OfferDetail mDetail;
    private String mState;
    private OfferDetailInfoAdapter mOffInfoAdapter;
    private RecyclerView mRvListInfo;

    private TextView mTitle;
    private TextView mUser;
    private TextView mDistanceDesc;
    private TextView mPhone;
    private TextView mEmail;
    private Button mBtnAccept;
    private Button mBtnReject;
    private View mUserIconView;
    private View mAddressIconView;
    private TextView mNeighborhoodCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("Oferta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mTitle=(TextView)findViewById(R.id.title);
        mUser=(TextView)findViewById(R.id.user);
        mDistanceDesc=(TextView)findViewById(R.id.distanceDesc);
        mEmail=(TextView)findViewById(R.id.email);
        mPhone=(TextView)findViewById(R.id.phone);
        mBtnAccept=(Button)findViewById(R.id.btnAccept);
        mBtnReject=(Button)findViewById(R.id.btnReject);
        mUserIconView=findViewById(R.id.userIconView);
        mAddressIconView=findViewById(R.id.addressIconView);
        mNeighborhoodCity=(TextView) findViewById(R.id.neighborhoodCity);


        mRvListInfo = (RecyclerView)findViewById(R.id.rvListInfo);
        mRvListInfo.setLayoutManager(new LinearLayoutManager(this));

        mOffer = (Offer) getIntent().getParcelableExtra("offer");
        mState = getIntent().getStringExtra("state");

        GetOfferDetailTask.Task task = new GetOfferDetailTask().new Task();
        task.delegate = this;
        task.execute(mOffer.get_links());

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    @Override
    public void processFinish(Object output) {
        mDetail = (OfferDetail)output;
        fillViews();
    }


    void fillViews (){
        if("unread".equals(mState)){
            mUserIconView.setBackgroundResource(R.mipmap.account_blue);
            mAddressIconView.setBackgroundResource(R.mipmap.map_blue);
        }else{
            mUserIconView.setBackgroundResource(R.mipmap.account_gray);
            mAddressIconView.setBackgroundResource(R.mipmap.map_gray);
        }
        Address ad = mDetail.get_embedded().getAddress();
        mTitle.setText(mDetail.getTitle());
        mUser.setText(mDetail.get_embedded().getUser().getName());
        mNeighborhoodCity.setText(ad.getNeighborhood()+" - "+ad.getCity());
        mDistanceDesc.setText("a "+mDetail.getIntDistance() / 1000+"Km de você");
        mPhone.setText(mDetail.get_embedded().getUser().get_embedded().getPhones().get(0).getNumber());
        mEmail.setText(mDetail.get_embedded().getUser().getEmail());


        mBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),LeadDetailActivity.class)
                        .putExtra("offer_accepted",mDetail.get_links().getAccept()));
                finish();
            }
        });
        mBtnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //startActivity(new Intent(getBaseContext(),LeadDetailActivity.class)
                        //.putExtra("offer_rejected",mDetail.get_links().getReject()));
            }
        });


        mRvListInfo = (RecyclerView)findViewById(R.id.rvListInfo);
        mRvListInfo.setLayoutManager(new LinearLayoutManager(this));
        mOffInfoAdapter = new OfferDetailInfoAdapter(mDetail,mState);
        mRvListInfo.setAdapter(mOffInfoAdapter);
        mRvListInfo.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMMap = googleMap;
        mMMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Geolocation local;
        if(mDetail == null){
            return;
        } else {
            local = mDetail.get_embedded().getAddress().getGeolocation();
        }

        LatLng latLon = new LatLng(local.getLatitude(), local.getLongitude());
        mMMap.addCircle(new CircleOptions()
                .center(latLon)
                .radius(30)
                .strokeColor(Color.BLUE)
                .strokeWidth(2f)
                .fillColor(Color.argb(1, 0, 0, 255))
                .center(latLon)
            );
        mMMap.moveCamera(CameraUpdateFactory.newLatLng(latLon));
        mMMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        //mapView.invalidate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.invalidate();
        //mMapView.onResume();
    }
}
