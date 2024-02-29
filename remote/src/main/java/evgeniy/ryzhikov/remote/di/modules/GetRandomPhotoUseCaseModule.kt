package evgeniy.ryzhikov.remote.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.remote.data.apod.repository.ApodRepository
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase
import javax.inject.Singleton

@Module(
    includes = [
        ApodModule::class
    ]
)
class GetRandomPhotoUseCaseModule {

    @Singleton
    @Provides
    fun provideGetRandomPhotoUseCase(repository: ApodRepository): GetRandomPhotoUseCase =
        GetRandomPhotoUseCase(repository)
}