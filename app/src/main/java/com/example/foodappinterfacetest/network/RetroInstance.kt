package com.example.foodappinterfacetest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        val BaseURL = "https://api.edamam.com"
        private var retroInstance: Retrofit? = null

        fun getRetroInstance(): Retrofit {
            if (retroInstance == null) {
                val loggingInterceptor = provideHttpLoggingInterceptor()
                val okHttpClient = provideOkHttpClient(loggingInterceptor)
                retroInstance =  Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(BaseURL)
                    .build()
            }
            return retroInstance!!
        }

        private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return  HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    }


}