package evgeniy.ryzhiikov.feature_favorites.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhiikov.feature_favorites.utils.FavoritesViewModelFactory
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.GetAllFavoritesUseCase
import javax.inject.Singleton

@Module
class ProvideFavoritesViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideFavoritesViewModelFactory(
        deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
        getAllFavoritesUseCase: GetAllFavoritesUseCase,
        addToFavoriteUseCase: AddToFavoriteUseCase,
    ): FavoritesViewModelFactory =
        FavoritesViewModelFactory(
            deleteFromFavoriteUseCase = deleteFromFavoriteUseCase,
            getAllFavoritesUseCase = getAllFavoritesUseCase,
            addToFavoriteUseCase = addToFavoriteUseCase,
        )
}