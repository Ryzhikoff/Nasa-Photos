package evgeniy.ryzhikov.database_module.di

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.database_module.data.FavoriteRepository
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.GetAllFavoritesUseCase
import evgeniy.ryzhikov.database_module.domain.IsFavoritesUseCase
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class
    ]
)
class FavoritesUseCaseModule {

    @Singleton
    @Provides
    fun provideAddToFavoriteUseCase(favoriteRepository: FavoriteRepository): AddToFavoriteUseCase =
        AddToFavoriteUseCase(favoriteRepository)

    @Singleton
    @Provides
    fun provideDeleteFromFavoriteUseCase(favoriteRepository: FavoriteRepository): DeleteFromFavoriteUseCase =
        DeleteFromFavoriteUseCase(favoriteRepository)

    @Singleton
    @Provides
    fun provideGetAllFavoritesUseCase(favoriteRepository: FavoriteRepository): GetAllFavoritesUseCase =
        GetAllFavoritesUseCase(favoriteRepository)

    @Singleton
    @Provides
    fun provideIsFavoriteUseCase(favoriteRepository: FavoriteRepository): IsFavoritesUseCase =
        IsFavoritesUseCase(repository = favoriteRepository)
}