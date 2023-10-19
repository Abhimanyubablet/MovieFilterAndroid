package com.example.moviefilterproject

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApiCalling {

        @GET("b/6458ec108e4aa6225e98d54d")
        fun getMovie(): Observable<MoviesDataModel>
        companion object Factory {
            fun create(): MovieApiCalling {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.jsonbin.io/v3/")
                    .build()
                return retrofit.create(MovieApiCalling::class.java)
            }
        }

}