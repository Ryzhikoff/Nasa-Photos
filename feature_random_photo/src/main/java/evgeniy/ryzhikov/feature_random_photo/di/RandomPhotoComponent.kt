package evgeniy.ryzhikov.feature_random_photo.di

import dagger.Component
import evgeniy.ryzhikov.core.di.ProvideContextModule
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.database_module.di.FavoritesUseCaseModule
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoViewModelFactoryModule
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoFragment
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoViewModel
import evgeniy.ryzhikov.remote.di.modules.ApodModule
import evgeniy.ryzhikov.remote.di.modules.GetRandomPhotoUseCaseModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApodModule::class,
        GetRandomPhotoUseCaseModule::class,
        DatabaseModule::class,
        FavoritesUseCaseModule::class,
        RandomPhotoViewModelFactoryModule::class,
        ProvideContextModule::class
    ]
)
interface RandomPhotoComponent {
    fun inject(randomPhotoFragment: RandomPhotoFragment)

    fun inject(randomPhotoViewModel: RandomPhotoViewModel)
}