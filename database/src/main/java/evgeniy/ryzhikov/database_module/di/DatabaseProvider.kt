package evgeniy.ryzhikov.database_module.di

import evgeniy.ryzhikov.database_module.data.NasaPhotosDatabase

interface DatabaseProvider {
    fun provideDatabase(): NasaPhotosDatabase
}