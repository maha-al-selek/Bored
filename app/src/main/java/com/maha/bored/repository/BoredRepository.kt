package com.maha.bored.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maha.bored.models.BoredResponse
import com.maha.bored.network.BoredService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BoredRepository {
    private val boredService: BoredService
    private val boredResponseLiveData: MutableLiveData<BoredResponse>?
    fun getActivity() {
        boredService.getActivity()
            .enqueue(object : Callback<BoredResponse?> {
                override fun onResponse(
                    call: Call<BoredResponse?>?,
                    response: Response<BoredResponse?>
                ) {
                    if (response.body() != null) {
                        boredResponseLiveData?.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<BoredResponse?>?, t: Throwable?) {
                    boredResponseLiveData?.postValue(null)
                }
            })
    }

    fun getBoredResponseLiveDataLiveData(): LiveData<BoredResponse>? {
        return boredResponseLiveData
    }

    companion object {
        private const val BOOK_SEARCH_SERVICE_BASE_URL = "https://www.boredapi.com/"
    }

    init {
        boredResponseLiveData = MutableLiveData<BoredResponse>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        boredService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoredService::class.java)
    }
}