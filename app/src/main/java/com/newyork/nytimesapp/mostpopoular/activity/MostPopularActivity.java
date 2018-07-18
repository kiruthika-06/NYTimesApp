package com.newyork.nytimesapp.mostpopoular.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.newyork.nytimesapp.R;
import com.newyork.nytimesapp.mostpopoular.adapter.PopularNewsAdapter;
import com.newyork.nytimesapp.mostpopoular.model.ResultsItem;

import java.util.ArrayList;

public class MostPopularActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Animation animationUp, animationDown;

    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular);

        initializeToolbarAndRecyclerView();
        initProgressBar();


        presenter = new MainPresenterImpl(this, new GetPopularNewsImpl());
        presenter.requestDataFromServer();
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void OnItemClick(ResultsItem popularNews) {
            /*Toast.makeText(MostPopularActivity.this,
                    "List title:  " + popularNews.getByline(),
                    Toast.LENGTH_LONG).show();*/
        }


    };

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<ResultsItem> popularNewsArrayList) {

        PopularNewsAdapter adapter = new PopularNewsAdapter(this,popularNewsArrayList , recyclerItemClickListener);
        Log.wtf("URL Called", popularNewsArrayList.toString() + "");

        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onResponseFailure(Throwable throwable) {

        Toast.makeText(MostPopularActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();

        Log.wtf("URL Called", throwable.getMessage()+ "");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MostPopularActivity.this);
        recyclerView.setLayoutManager(layoutManager);


    }


    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

}
