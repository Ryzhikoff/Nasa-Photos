package evgeniy.ryzhikov.nasaphotos.di

import dagger.Component
import evgeniy.ryzhikov.apod_module.di.ApodProvider
import evgeniy.ryzhikov.database_module.di.DatabaseComponent
import evgeniy.ryzhikov.nasaphotos.di.modules.DomainModule
import evgeniy.ryzhikov.nasaphotos.ui.home.HomeFragment
import evgeniy.ryzhikov.nasaphotos.ui.home.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        ApodProvider::class,
        DatabaseComponent::class
    ],
    modules = [DomainModule::class]
)
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(homeViewModel: HomeViewModel)
}