package evgeniy.ryzhikov.apod_module.di

import dagger.Component
import evgeniy.ryzhikov.apod_module.di.modules.ApodModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [ApodModule::class]
)
interface ApodComponent : ApodProvider