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

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import br.com.testmaster.domain.Accept;
import br.com.testmaster.domain.Address;
import br.com.testmaster.domain.Geolocation;
import br.com.testmaster.domain.Lead;
import br.com.testmaster.domain.LeadDetail;
import br.com.testmaster.domain.Links;
import br.com.testmaster.domain.Self;
import br.com.testmaster.remote.AsyncResponse;
import br.com.testmaster.remote.task.GetLeadDetailTask;
import br.com.testmaster.util.CallApp;
import br.com.testmaster.view.adapter.LeadDetailInfoAdapter;

public class LeadDetailActivity extends AppCompatActivity implements OnMapReadyCallback, AsyncResponse {

    private MapView mMapView;
    private GoogleMap mMMap;
    private Lead mLead;
    private LeadDetail mDetail;
    private String mState;
    private LeadDetailInfoAdapter mLeaInfoAdapter;
    private RecyclerView mRvListInfo;
    private TextView mTitle;
    private TextView mUser;
    private TextView mDistanceDesc;
    private TextView mPhone;
    private TextView mEmail;
    private Button mBtnCallPhone;
    private Button mBtnWzp;
    private View mUserIconView;
    private View mAddressIconView;
    private TextView mNeighborhoodCity;
    private Toolbar mToolbar;
    private Accept mAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        processIntentRequest();
        mTitle = (TextView) findViewById(R.id.title);
        mUser = (TextView) findViewById(R.id.user);
        mDistanceDesc = (TextView) findViewById(R.id.distanceDesc);
        mEmail = (TextView) findViewById(R.id.email);
        mPhone = (TextView) findViewById(R.id.phone);
        mBtnCallPhone = (Button) findViewById(R.id.btnCallPhone);
        mBtnWzp = (Button) findViewById(R.id.btnWzp);
        mUserIconView = findViewById(R.id.userIconView);
        mAddressIconView = findViewById(R.id.addressIconView);
        mNeighborhoodCity = (TextView) findViewById(R.id.neighborhoodCity);
        mRvListInfo = (RecyclerView) findViewById(R.id.rvListInfo);
        mRvListInfo.setLayoutManager(new LinearLayoutManager(this));
        callWebService();
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    /**
     * Obtém os dados do Lead via webservice.
     */
    void callWebService(){
        GetLeadDetailTask.Task task = new GetLeadDetailTask(LeadDetailActivity.this).new Task();
        task.delegate = this;
        task.execute(mLead.get_links());
    }

    /**
     *Obtêm objetos vindos via Intent. Se objeto Lead estiver nulo
     * então significa que uma oferta foi aceita e obtem a URI da Intent
     * para processar o seu carregamento via webservice.
     */
    void processIntentRequest(){
        mLead = (Lead) getIntent().getParcelableExtra("lead");
        if(mLead == null){
            mAccept = getIntent().getParcelableExtra("offer_accepted");
            mLead = new Lead(new Links(new Self(mAccept.getHref())));
        }
    }

    /**
     *Recupera objeto retornado via webservice e processa carregamento das views.
     */
    @Override
    public void processFinish(Object output) {
        mDetail = (LeadDetail) output;
        fillViews();
    }

    /**
     *Preenche as views com os dados retornados.
     */
    void fillViews() {
        mToolbar.setTitle(mDetail.get_embedded().getUser().getName());
        mUserIconView.setBackgroundResource(R.mipmap.account_green);
        mAddressIconView.setBackgroundResource(R.mipmap.map_green);
        Address ad = mDetail.get_embedded().getAddress();
        mTitle.setText(mDetail.getTitle());
        mUser.setText(mDetail.get_embedded().getUser().getName());
        mNeighborhoodCity.setText(ad.getNeighborhood() + " - " + ad.getCity());
        mDistanceDesc.setText("a " + mDetail.getIntDistance() / 1000 + "Km de você");
        mPhone.setText(mDetail.get_embedded().getUser().get_embedded().getPhones().get(0).getNumber());
        mEmail.setText(mDetail.get_embedded().getUser().getEmail());

        mBtnCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    CallApp.dialPhone(mDetail.get_embedded().getUser().get_embedded().getPhones().get(0).getNumber(),LeadDetailActivity.this);
            }
        });
        mBtnWzp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mDetail.get_embedded().getUser().getName()
                        +" "+ mDetail.get_embedded().getUser().get_embedded().getPhones().get(0).getNumber();

                boolean ok = CallApp.shareWithWhatsApp(text,LeadDetailActivity.this);

                if(!ok){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LeadDetailActivity.this);
                    dialog.setMessage(R.string.wzp_not_available);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface di, int arg) {
                            di.dismiss();
                        }
                    });
                    dialog.setTitle(LeadDetailActivity.this.getResources().getString(R.string.warning));
                    dialog.show();
                }

            }
        });
        mRvListInfo = (RecyclerView)findViewById(R.id.rvListInfo);
        mRvListInfo.setLayoutManager(new LinearLayoutManager(this));
        mLeaInfoAdapter = new LeadDetailInfoAdapter(mDetail);
        mRvListInfo.setAdapter(mLeaInfoAdapter);
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
