package evgeniy.ryzhikov.nasaphotos

import android.app.Application
import evgeniy.ryzhikov.feature_random_photo.di.modules.DaggerRandomPhotoComponent
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoComponent
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoComponentProvider
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoViewModelFactoryModule
import evgeniy.ryzhikov.remote.di.modules.GetRandomPhotoUseCaseModule
import evgeniy.ryzhikov.remote.di.modules.RemoteModule

class App : Application(), RandomPhotoComponentProvider {

    private val remoteModule by lazy {
        RemoteModule()
    }

    override fun getRandomPhotoComponent(): RandomPhotoComponent =
        DaggerRandomPhotoComponent.builder()
            .getRandomPhotoUseCaseModule(GetRandomPhotoUseCaseModule())
            .randomPhotoViewModelFactoryModule(RandomPhotoViewModelFactoryModule())
            .remoteModule(remoteModule)
            .build()

}