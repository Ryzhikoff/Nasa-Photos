package evgeniy.ryzhikov.database_module.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import evgeniy.ryzhikov.database_module.data.TABLE_NAME_IMAGE_INFO

@Dao
interface ImageInfoDao {
    @Query("SELECT * FROM $TABLE_NAME_IMAGE_INFO")
    fun getAllFavorites(): List<evgeniy.ryzhikov.database_module.data.entity.ImageInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(imageInfoEntity: evgeniy.ryzhikov.database_module.data.entity.ImageInfo)
}