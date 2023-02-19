package ru.vaimon.vkservices.services

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.vaimon.vkservices.models.VKService


interface RetrofitService {

    @GET("/semi-final-data.json")
    fun getVkServiceInfo() : Call<Array<VKService>>

    companion object {
        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl("https://mobile-olympiad-trajectory.hb.bizmrg.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .registerTypeAdapter(Array<VKService>::class.java, VKService.Companion.Deserializer())
                    .create()))
                .build()
                .create(RetrofitService::class.java)
        }
    }
}