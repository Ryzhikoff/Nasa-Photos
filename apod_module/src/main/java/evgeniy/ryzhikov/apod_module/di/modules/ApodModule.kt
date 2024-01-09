package evgeniy.ryzhikov.apod_module.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.apod_module.BuildConfig
import evgeniy.ryzhikov.apod_module.api.ApodApi
import evgeniy.ryzhikov.apod_module.api.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApodModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        })
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    
    @Singleton
    @Provides
    fun provideApodApi(retrofit: Retrofit): ApodApi = retrofit.create(ApodApi::class.java)
}