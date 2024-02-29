package evgeniy.ryzhikov.search_module.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.IsFavoritesUseCase
import evgeniy.ryzhikov.remote.domain.SearchUseCase
import evgeniy.ryzhikov.search_module.utils.SearchViewModelFactory
import javax.inject.Singleton

@Module
class SearchViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(
        searchUseCase: SearchUseCase,
        addToFavoriteUseCase: AddToFavoriteUseCase,
        deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
        isFavoritesUseCase: IsFavoritesUseCase,
    ): SearchViewModelFactory =
        SearchViewModelFactory(searchUseCase, addToFavoriteUseCase, deleteFromFavoriteUseCase, isFavoritesUseCase)
}