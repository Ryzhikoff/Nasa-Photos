package evgeniy.ryzhikov.database_module.data

import androidx.room.Database
import androidx.room.RoomDatabase
import evgeniy.ryzhikov.database_module.data.dao.ImageInfoDao
import evgeniy.ryzhikov.database_module.data.entity.ImageInfo

@Database(
    entities = [
        ImageInfo::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NasaPhotosDatabase : RoomDatabase() {
    abstract fun imageInfoDao(): ImageInfoDao
}