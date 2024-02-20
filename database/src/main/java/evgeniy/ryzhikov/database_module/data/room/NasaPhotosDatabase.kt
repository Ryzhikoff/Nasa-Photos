package evgeniy.ryzhikov.database_module.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import evgeniy.ryzhikov.database_module.data.DATABASE_VERSION
import evgeniy.ryzhikov.database_module.data.room.dao.ImageInfoDao
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

@Database(
    entities = [
        ImageInfoEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NasaPhotosDatabase : RoomDatabase() {
    abstract fun imageInfoDao(): ImageInfoDao
}