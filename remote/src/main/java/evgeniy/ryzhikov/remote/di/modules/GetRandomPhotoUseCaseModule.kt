package evgeniy.ryzhikov.remote.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.remote.data.repository.NasaRepository
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase
import javax.inject.Singleton

@Module(
    includes = [
        RemoteModule::class
    ]
)
class GetRandomPhotoUseCaseModule {

    @Singleton
    @Provides
    fun provideGetRandomPhotoUseCase(repository: NasaRepository): GetRandomPhotoUseCase =
        GetRandomPhotoUseCase(repository)
}