package com.example.prenacional.Model.Api

import com.example.prenacional.Model.Modelos.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Llamadas {
    @GET("Usuario")
    fun GetAllUsers(): Call<List<Usuario>>

    @GET("Usuario/{id}")
    fun GeUser(@Path("id") id: String): Call<List<Usuario>>


    @POST
    fun ActualizarUsuario():Call<*>


}