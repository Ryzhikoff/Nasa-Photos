package evgeniy.ryzhikov.database_module.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.database_module.data.DATABASE_NAME
import evgeniy.ryzhikov.database_module.data.FavoriteRepository
import evgeniy.ryzhikov.database_module.data.FavoriteRepositoryImpl
import evgeniy.ryzhikov.database_module.data.room.NasaPhotosDatabase
import evgeniy.ryzhikov.database_module.data.room.dao.ImageInfoDao
import javax.inject.Singleton

@Module
class DatabaseModule {

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
    fun provideFavoriteRepository(imageInfoDao: ImageInfoDao):FavoriteRepository =
        FavoriteRepositoryImpl(imageInfoDao)
}