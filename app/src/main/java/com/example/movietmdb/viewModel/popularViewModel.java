package com.example.movietmdb.viewModel;


import android.util.Log;
import android.widget.Toast;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movietmdb.model.popularMoviesResponse;
import com.example.movietmdb.model.popularMoviesResults;
import com.example.movietmdb.repository.Repository;
import com.example.movietmdb.retrofit.Retrofit;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class popularViewModel extends ViewModel {
    private static final String TAG = "popularViewModel";
    private Repository repository;
    MutableLiveData<List<popularMoviesResults>> popularMovies=new MutableLiveData<>();
    popularMoviesResponse list;

    @ViewModelInject
    public popularViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<popularMoviesResults>> getPopularMovies() {
        return popularMovies;
    }

    public void getPopularMovie(int pageNumber)
    {
        repository.getPopularMovies(pageNumber).
                subscribeOn(Schedulers.io())
                .map(new Function<popularMoviesResponse, List<popularMoviesResults>>() {
                    @Override
                    public List<popularMoviesResults> apply(popularMoviesResponse popularMoviesResponse) throws Throwable {
                       List<popularMoviesResults>results=popularMoviesResponse.getResults();
                       list=popularMoviesResponse;
                       return results;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularMoviesResults -> popularMovies.setValue(popularMoviesResults),error-> Log.e(TAG, "getPopularMovie44543: "+list.getResults().size() ));
    }
}
