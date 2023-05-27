package com.example.searchlist.data.service

import com.example.searchlist.data.model.PlpResults
import com.example.searchlist.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("v3/plp?search-string={{originalSearchTerm}}&page-number=1")
    suspend fun getProduct(@Path("originalSearchTerm") originalSearchTerm: String ): PlpResults
}

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}