package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    val api : todoapi by lazy{
//        Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(todoapi :: class.java)
//    }

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
    }

    val api = getInstance().create(todoapi::class.java)
}