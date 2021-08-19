package com.example.movietmdb.repository;

import com.example.movietmdb.model.popularMoviesResponse;
import com.example.movietmdb.retrofit.Retrofit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private Retrofit retrofitInterface;
    private static String API_KEY="d9e7279adfa8d9d66f998e61158c2c12";
    @Inject
    public Repository(Retrofit retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }
    public Observable<popularMoviesResponse> getPopularMovies(int pageNumber)
    {
        return retrofitInterface.getPopularMovies(API_KEY,pageNumber);
    }
}
