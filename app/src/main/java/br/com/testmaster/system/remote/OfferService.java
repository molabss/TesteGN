package br.com.testmaster.system.remote;

import br.com.testmaster.domain.OfferDetail;
import br.com.testmaster.domain.OfferWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Moisés Santana on 06/10/2016.
 */

public interface OfferService {

    @GET("offers")
    Call<OfferWrapper> listOffers();


    @GET
    Call<OfferDetail>getDetail(@Url String url);


}
