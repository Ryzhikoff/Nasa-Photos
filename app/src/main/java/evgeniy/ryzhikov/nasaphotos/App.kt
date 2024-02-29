package evgeniy.ryzhikov.nasaphotos

import android.app.Application
import evgeniy.ryzhiikov.feature_favorites.di.DaggerFavoritesComponent
import evgeniy.ryzhiikov.feature_favorites.di.FavoritesComponent
import evgeniy.ryzhiikov.feature_favorites.di.modules.FavoritesComponentProvider
import evgeniy.ryzhiikov.feature_favorites.di.modules.ProvideFavoritesViewModelFactoryModule
import evgeniy.ryzhikov.core.di.ProvideContextModule
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.database_module.di.FavoritesUseCaseModule
import evgeniy.ryzhikov.feature_random_photo.di.DaggerRandomPhotoComponent
import evgeniy.ryzhikov.feature_random_photo.di.RandomPhotoComponent
import evgeniy.ryzhikov.feature_random_photo.di.RandomPhotoComponentProvider
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoViewModelFactoryModule
import evgeniy.ryzhikov.remote.di.modules.ApodModule
import evgeniy.ryzhikov.remote.di.modules.GetRandomPhotoUseCaseModule
import evgeniy.ryzhikov.remote.di.modules.SearchUseCaseModule
import evgeniy.ryzhikov.search_module.di.DaggerSearchComponent
import evgeniy.ryzhikov.search_module.di.SearchComponent
import evgeniy.ryzhikov.search_module.di.modules.SearchComponentProvider
import evgeniy.ryzhikov.search_module.di.modules.SearchViewModelFactoryModule

class App : Application(),
    RandomPhotoComponentProvider,
    FavoritesComponentProvider,
    SearchComponentProvider {

    private val provideContextModule by lazy {
        ProvideContextModule(this)
    }

    private val apodModule by lazy {
        ApodModule()
    }

    private val databaseModule by lazy {
        DatabaseModule()
    }


    override fun getRandomPhotoComponent(): RandomPhotoComponent =
        DaggerRandomPhotoComponent.builder()
            .getRandomPhotoUseCaseModule(GetRandomPhotoUseCaseModule())
            .randomPhotoViewModelFactoryModule(RandomPhotoViewModelFactoryModule())
            .apodModule(apodModule)
            .databaseModule(databaseModule)
            .favoritesUseCaseModule(FavoritesUseCaseModule())
            .provideContextModule(provideContextModule)
            .build()

    override fun getFavoritesComponent(): FavoritesComponent =
        DaggerFavoritesComponent.builder()
            .provideContextModule(provideContextModule)
            .databaseModule(databaseModule)
            .favoritesUseCaseModule(FavoritesUseCaseModule())
            .provideFavoritesViewModelFactoryModule(ProvideFavoritesViewModelFactoryModule())
            .build()

    override fun getSearchComponent(): SearchComponent =
        DaggerSearchComponent.builder()
            .provideContextModule(provideContextModule)
            .searchUseCaseModule(SearchUseCaseModule())
            .searchViewModelFactoryModule(SearchViewModelFactoryModule())
            .searchUseCaseModule(SearchUseCaseModule())
            .databaseModule(databaseModule)
            .favoritesUseCaseModule(FavoritesUseCaseModule())
            .build()

}