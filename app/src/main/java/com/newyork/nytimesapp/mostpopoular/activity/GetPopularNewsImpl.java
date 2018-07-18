package com.newyork.nytimesapp.mostpopoular.activity;

import android.util.Log;

import com.newyork.nytimesapp.mostpopoular.model.ResponseList;
import com.newyork.nytimesapp.mostpopoular.network.RetrofitInstance;
import com.newyork.nytimesapp.mostpopoular.ny_interface.GetPopularNewsDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPopularNewsImpl implements MainContract.GetNewsIntractor{
    @Override
    public void getPopularNewsArrayList(final OnFinishedListener onFinishedListener) {

        GetPopularNewsDataService service = RetrofitInstance.getRetrofitInstance().create(GetPopularNewsDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ResponseList> call = service.getNoticeData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseList>() {
            @Override
            public void onResponse(Call<ResponseList> call, Response<ResponseList> response) {
                onFinishedListener.onFinished(response.body().getResults());

                Log.wtf("URL ResponseList", response.body().toString()+ "");


            }


            @Override
            public void onFailure(Call<ResponseList> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.wtf("URL ResponseList",  call.toString() + "");
            }
        });
    }
}
