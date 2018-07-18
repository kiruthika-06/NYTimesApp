package com.newyork.nytimesapp.mostpopoular.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newyork.nytimesapp.R;
import com.newyork.nytimesapp.mostpopoular.activity.RecyclerItemClickListener;
import com.newyork.nytimesapp.mostpopoular.model.ResultsItem;

import java.util.ArrayList;

public class PopularNewsAdapter extends RecyclerView.Adapter<PopularNewsAdapter.PopularNewsViewHolder> {
    private ArrayList<ResultsItem> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    boolean isExpanded=false;
    Context context;

    public PopularNewsAdapter(Context context,ArrayList<ResultsItem> dataList , RecyclerItemClickListener recyclerItemClickListener
            ) {
        this.dataList = dataList;
        this.context=context;
        this.recyclerItemClickListener = recyclerItemClickListener;

    }


    @Override
    public PopularNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_news, parent, false);
        return new PopularNewsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final PopularNewsViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNewsTitle.setText(dataList.get(position).getTitle());
        holder.txtNewsBrief.setText(dataList.get(position).getJsonMemberAbstract());
        holder.txtNewsAuthor.setText(dataList.get(position).getByline());
        holder.txtNewsDate.setText(dataList.get(position).getPublishedDate());

        holder.tvNewsDescription.setText(dataList.get(position).getSection());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.OnItemClick(dataList.get(position));
                if(isExpanded)
                {
                    holder.llContentLayout.setVisibility(View.GONE);
                }else
                {
                    holder.llContentLayout.setVisibility(View.VISIBLE);
                    isExpanded=false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PopularNewsViewHolder extends RecyclerView.ViewHolder {

        TextView txtNewsTitle, txtNewsBrief, txtNewsAuthor,txtNewsDate,tvNewsDescription;
        LinearLayout llContentLayout;

        PopularNewsViewHolder(View itemView) {
            super(itemView);
            txtNewsTitle =  itemView.findViewById(R.id.txt_news_title);
            txtNewsBrief =  itemView.findViewById(R.id.txt_news_brief);
            txtNewsAuthor =  itemView.findViewById(R.id.txt_news_author);
            txtNewsDate=itemView.findViewById(R.id.txt_news_date);
            tvNewsDescription=itemView.findViewById(R.id.newsDescription);
            llContentLayout=itemView.findViewById(R.id.content_layout);

        }
    }
}
