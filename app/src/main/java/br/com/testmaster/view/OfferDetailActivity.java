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

package br.com.testmaster.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.testmaster.R;
import br.com.testmaster.domain.Geolocation;
import br.com.testmaster.domain.Offer;
import br.com.testmaster.domain.OfferDetail;
import br.com.testmaster.system.remote.AsyncResponse;
import br.com.testmaster.system.remote.task.GetOfferDetail;

public class OfferDetailActivity extends AppCompatActivity implements OnMapReadyCallback, AsyncResponse {

    MapView mapView;
    GoogleMap mMap;
    Offer offer;
    OfferDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("Oferta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        offer = (Offer) getIntent().getParcelableExtra("offer");

        GetOfferDetail.Task task = new GetOfferDetail().new Task();
        task.delegate = this;
        task.execute(offer.get_links());

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }








    @Override
    public void processFinish(Object output) {
        detail = (OfferDetail)output;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSet = mMap.getUiSettings();
        uiSet.setZoomControlsEnabled(true);
        uiSet.setZoomGesturesEnabled(true);

        Geolocation local;
        if(detail == null){
            return;
        } else {
            local = detail.get_embedded().getAddress().getGeolocation();
        }

        LatLng latLon = new LatLng(local.getLatitude(), local.getLongitude());
        mMap.addCircle(new CircleOptions()
                .center(latLon)
                .radius(30)
                .strokeColor(Color.BLUE)
                .strokeWidth(2f)
                .fillColor(Color.argb(1, 0, 0, 255))
                .center(latLon)
            );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLon));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }

}
