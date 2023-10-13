package com.example.prenacional.Model.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private var retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.194.147.75:1234/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    var builder = retrofit.create(Llamadas::class.java)
}