package evgeniy.ryzhikov.database_module.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import evgeniy.ryzhikov.database_module.data.TABLE_NAME_IMAGE_INFO
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

@Dao
interface ImageInfoDao {
    @Query("SELECT * FROM $TABLE_NAME_IMAGE_INFO")
    suspend fun getAllFavorites(): List<ImageInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(imageInfoEntity: ImageInfoEntity)

    @Query("DELETE FROM $TABLE_NAME_IMAGE_INFO WHERE title LIKE :title AND link LIKE :link")
    suspend fun deleteById(title: String, link: String)

    @Query("SELECT * FROM $TABLE_NAME_IMAGE_INFO WHERE title LIKE :title AND link LIKE :link")
    suspend fun getImageInfo(title: String, link: String): ImageInfoEntity?
}