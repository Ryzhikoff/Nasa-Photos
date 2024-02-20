package evgeniy.ryzhikov.feature_random_photo.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
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
    fun provideRandomPhotoViewModelFactory(
        getRandomPhotoUseCase: GetRandomPhotoUseCase,
        addToFavoriteUseCase: AddToFavoriteUseCase,
        deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    ) : RandomPhotoViewModelFactory =
        RandomPhotoViewModelFactory(
            getRandomPhotoUseCase, deleteFromFavoriteUseCase, addToFavoriteUseCase
        )
}