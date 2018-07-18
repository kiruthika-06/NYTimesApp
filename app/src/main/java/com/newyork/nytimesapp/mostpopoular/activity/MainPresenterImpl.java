package com.newyork.nytimesapp.mostpopoular.activity;

import android.util.Log;

import com.newyork.nytimesapp.mostpopoular.model.ResultsItem;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter,MainContract.GetNewsIntractor.OnFinishedListener {

    MainContract.MainView mainView;
    MainContract.GetNewsIntractor getNewsIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNewsIntractor getNewsIntractor) {
        this.mainView = mainView;
        this.getNewsIntractor = getNewsIntractor;
    }


    @Override
    public void onDestroy() {

        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getNewsIntractor.getPopularNewsArrayList(this);
    }

    @Override
    public void requestDataFromServer() {

        mainView.showProgress();
        getNewsIntractor.getPopularNewsArrayList(this);
    }



    @Override
    public void onFinished(ArrayList<ResultsItem> popularNewsArrayList) {

        Log.wtf("URL Called", popularNewsArrayList + "");

        if(mainView != null){
            mainView.setDataToRecyclerView(popularNewsArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
