package evgeniy.ryzhikov.nasaphotos.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.apod_module.api.ApodApi
import evgeniy.ryzhikov.apod_module.api.ApodRepository
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApodRepository(retrofitService: ApodApi): ApodRepository = ApodRepository(retrofitService)
}