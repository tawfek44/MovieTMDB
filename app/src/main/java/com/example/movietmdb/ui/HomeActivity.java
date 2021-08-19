package com.example.movietmdb.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.assist.AssistStructure;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.movietmdb.R;
import com.example.movietmdb.adapters.popularMoviesAdapter;
import com.example.movietmdb.model.popularMoviesResults;
import com.example.movietmdb.viewModel.popularViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<popularMoviesResults>popularMoviesResults;
    private popularViewModel popularMoviesViewModel;
    private popularMoviesAdapter popularMoviesAdapter;
    private int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager2=findViewById(R.id.viewPagerSlider);

        popularMoviesResults=new ArrayList<>();
        popularMoviesViewModel= new ViewModelProvider(this).get(popularViewModel.class);
        popularMoviesViewModel.getPopularMovie(pageNumber);
        popularMoviesViewModel.getPopularMovies().observe(this, new Observer<List<com.example.movietmdb.model.popularMoviesResults>>() {
            @Override
            public void onChanged(List<popularMoviesResults> popularMoviesResult) {
                popularMoviesResults=popularMoviesResult;
                popularMoviesAdapter=new popularMoviesAdapter(popularMoviesResults,viewPager2,HomeActivity.this);
                viewPager2.setAdapter(popularMoviesAdapter);
            }
        });

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

    }
}