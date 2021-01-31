package com.example.muvi.di

import com.example.muvi.BuildConfig
import com.example.muvi.utils.ApiConfig.API_KEY
import com.example.muvi.utils.ApiConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}
