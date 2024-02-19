package evgeniy.ryzhikov.database_module.di

import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(
    modules = [
        DatabaseModule::class
    ]
)
interface DatabaseComponent