package com.example.kennedycruiser.model.remote

import com.example.kennedycruiser.BuildConfig
import com.example.kennedycruiser.model.ShipResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FleetApi {
    //https://www.ncl.com/cms-service/cruise-ships/SKY
    //https://www.ncl.com/cms-service/cruise-ships/BLISS
    //https://www.ncl.com/cms-service/cruise-ships/ESCAPE
    @GET(BuildConfig.END_POINT)
    suspend fun getFleet(
        @Path("ship") shipName: String
    ): Response<ShipResponse>

    companion object{
        fun initRetrofit(): FleetApi{
            return Retrofit.Builder()
                .client(getClient())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FleetApi::class.java)
        }

        private fun getClient(): OkHttpClient{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder().addInterceptor(logging).build()
        }
    }
}