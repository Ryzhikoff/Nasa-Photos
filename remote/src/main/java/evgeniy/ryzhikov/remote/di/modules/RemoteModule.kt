package evgeniy.ryzhikov.remote.di.modules

import android.widget.RemoteViews.RemoteResponse
import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.remote.BuildConfig
import evgeniy.ryzhikov.remote.data.Constants
import evgeniy.ryzhikov.remote.data.NasaApi
import evgeniy.ryzhikov.remote.data.repository.NasaRepository
import evgeniy.ryzhikov.remote.data.repository.NasaRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {
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
    fun provideNasaApi(retrofit: Retrofit): NasaApi =
        retrofit.create(NasaApi::class.java)

    @Singleton
    @Provides
    fun provideNasaRepository(nasaApi: NasaApi): NasaRepository =
        NasaRepositoryImpl(nasaApi)
}