package evgeniy.ryzhikov.database_module.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.database_module.data.DATABASE_NAME
import evgeniy.ryzhikov.database_module.data.DatabaseRepository
import evgeniy.ryzhikov.database_module.data.NasaPhotosDatabase
import evgeniy.ryzhikov.database_module.data.dao.ImageInfoDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context
    @Singleton
    @Provides
    fun provideNasaPhotosDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            NasaPhotosDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideImageInfoDao(nasaPhotosDatabase: NasaPhotosDatabase) =
        nasaPhotosDatabase.imageInfoDao()

    @Singleton
    @Provides
    fun provideDatabaseRepository(imageInfoDao: ImageInfoDao) =
        DatabaseRepository(imageInfoDao)
}