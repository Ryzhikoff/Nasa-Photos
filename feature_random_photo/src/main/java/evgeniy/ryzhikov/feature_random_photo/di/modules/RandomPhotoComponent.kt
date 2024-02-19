package evgeniy.ryzhikov.feature_random_photo.di.modules

import dagger.Component
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoFragment
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoViewModel
import evgeniy.ryzhikov.remote.di.modules.GetRandomPhotoUseCaseModule
import evgeniy.ryzhikov.remote.di.modules.RemoteModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        GetRandomPhotoUseCaseModule::class,
        RandomPhotoViewModelFactoryModule::class,
    ]
)
interface RandomPhotoComponent {
    fun inject(randomPhotoFragment: RandomPhotoFragment)

    fun inject(randomPhotoViewModel: RandomPhotoViewModel)
}