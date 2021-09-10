package com.maha.bored.network

import com.maha.bored.models.BoredResponse

import retrofit2.Call
import retrofit2.http.GET

interface BoredService {
    @GET("api/activity/")
    fun getActivity(): Call<BoredResponse>
}