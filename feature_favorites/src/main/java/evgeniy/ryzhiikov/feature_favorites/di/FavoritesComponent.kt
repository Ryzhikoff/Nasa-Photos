package evgeniy.ryzhiikov.feature_favorites.di

import dagger.Component
import evgeniy.ryzhiikov.feature_favorites.di.modules.ProvideFavoritesViewModelFactoryModule
import evgeniy.ryzhiikov.feature_favorites.ui.FavoritesFragment
import evgeniy.ryzhiikov.feature_favorites.ui.FavoritesViewModel
import evgeniy.ryzhikov.core.di.ProvideContextModule
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.database_module.di.FavoritesUseCaseModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ProvideContextModule::class,
        DatabaseModule::class,
        FavoritesUseCaseModule::class,
        ProvideFavoritesViewModelFactoryModule::class,
    ]
)
interface FavoritesComponent {
    fun inject(favoritesFragment: FavoritesFragment)

    fun inject(favoritesViewModel: FavoritesViewModel)
}