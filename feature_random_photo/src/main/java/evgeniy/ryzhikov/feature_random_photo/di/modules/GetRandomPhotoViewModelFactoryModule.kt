package evgeniy.ryzhikov.feature_random_photo.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.feature_random_photo.utils.RandomPhotoViewModelFactory
import evgeniy.ryzhikov.remote.di.modules.GetRandomPhotoUseCaseModule
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase
import javax.inject.Singleton

@Module(
    includes = [
        GetRandomPhotoUseCaseModule::class
    ]
)
class RandomPhotoViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideRandomPhotoViewModelFactory(getRandomPhotoUseCase: GetRandomPhotoUseCase) : RandomPhotoViewModelFactory =
        RandomPhotoViewModelFactory(getRandomPhotoUseCase)
}