package evgeniy.ryzhikov.features_details.di

import dagger.Component
import evgeniy.ryzhikov.core.di.ProvideContextModule
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.database_module.di.FavoritesUseCaseModule
import evgeniy.ryzhikov.features_details.ui.DetailsFragment
import evgeniy.ryzhikov.features_details.ui.DetailsViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        FavoritesUseCaseModule::class,
        ProvideContextModule::class,
    ]
)
interface DetailsComponent {

    fun inject(detailsFragment: DetailsFragment)

    fun inject(detailsViewModel: DetailsViewModel)
}