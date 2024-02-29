package evgeniy.ryzhikov.search_module.di

import dagger.Component
import evgeniy.ryzhikov.core.di.ProvideContextModule
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.database_module.di.FavoritesUseCaseModule
import evgeniy.ryzhikov.remote.di.modules.SearchModule
import evgeniy.ryzhikov.remote.di.modules.SearchUseCaseModule
import evgeniy.ryzhikov.search_module.di.modules.SearchViewModelFactoryModule
import evgeniy.ryzhikov.search_module.ui.SearchFragment
import evgeniy.ryzhikov.search_module.ui.SearchViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SearchModule::class,
        SearchUseCaseModule::class,
        ProvideContextModule::class,
        SearchViewModelFactoryModule::class,
        DatabaseModule::class,
        FavoritesUseCaseModule::class,
    ]
)
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)

    fun inject(searchViewModel: SearchViewModel)
}