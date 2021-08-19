package com.example.movietmdb.retrofit;

import com.example.movietmdb.model.popularMoviesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Retrofit {
    //d9e7279adfa8d9d66f998e61158c2c12
    @GET("movie/popular")
    Observable<popularMoviesResponse>getPopularMovies(@Query("api_key") String key,@Query("page") int page);
}
