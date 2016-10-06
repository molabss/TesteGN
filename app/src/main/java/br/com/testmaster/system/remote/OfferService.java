package br.com.testmaster.system.remote;

import br.com.testmaster.domain.OfferWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Moisés Santana on 06/10/2016.
 */

public interface OfferService {

    @GET("offers")
    Call<OfferWrapper> listOffers();

}
