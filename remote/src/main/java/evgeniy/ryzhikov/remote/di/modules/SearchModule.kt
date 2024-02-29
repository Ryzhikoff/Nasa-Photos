package evgeniy.ryzhikov.remote.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.remote.BuildConfig
import evgeniy.ryzhikov.remote.data.Constants
import evgeniy.ryzhikov.remote.data.images.ImageApi
import evgeniy.ryzhikov.remote.data.images.SearchRepositoryImpl
import evgeniy.ryzhikov.remote.data.images.repository.SearchRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class SearchModule {
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
            .baseUrl(Constants.BASE_URL_IMAGE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi =
        retrofit.create(ImageApi::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(imageApi: ImageApi): SearchRepository =
        SearchRepositoryImpl(imageApi)
}