package com.newyork.nytimesapp.mostpopoular.activity;


import com.newyork.nytimesapp.mostpopoular.model.ResultsItem;

import java.util.ArrayList;



public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetPopularNewsImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<ResultsItem> popularNewsArrayList);



        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNewsIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<ResultsItem> popularNewsArrayList);
            void onFailure(Throwable t);
        }

        void getPopularNewsArrayList(OnFinishedListener onFinishedListener);
    }
}
