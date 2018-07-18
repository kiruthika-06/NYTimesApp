package com.newyork.nytimesapp.mostpopoular.ny_interface;

import com.newyork.nytimesapp.mostpopoular.model.ResponseList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPopularNewsDataService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=302854cfc46a407688fb3f56313ecae1")
    Call<ResponseList> getNoticeData();


}