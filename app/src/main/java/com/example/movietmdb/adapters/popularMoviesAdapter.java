package com.example.movietmdb.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.movietmdb.R;
import com.example.movietmdb.model.popularMoviesResults;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class popularMoviesAdapter extends RecyclerView.Adapter<popularMoviesAdapter.SliderViewHolder> {
   private List<popularMoviesResults> popularMoviesResults;
   private ViewPager2 viewPager2;
   private Context context;

    public popularMoviesAdapter(List<com.example.movietmdb.model.popularMoviesResults> popularMoviesResults, ViewPager2 viewPager2, Context context) {
        this.popularMoviesResults = popularMoviesResults;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.popular_movie_slider,parent,false));
    }
    public void setList(ArrayList<popularMoviesResults> results)
    {
        this.popularMoviesResults=results;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        String imgUrl = "https://image.tmdb.org/t/p/w500"+popularMoviesResults.get(position).getBackdrop_path();
        Glide.with(context).load(imgUrl).into(holder.roundedImageView);
        if(position==popularMoviesResults.size()-2)
        {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return popularMoviesResults.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView =itemView.findViewById(R.id.popular_mov_slider);
        }
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
        popularMoviesResults.addAll(popularMoviesResults);
        notifyDataSetChanged();
        }
    };
}
