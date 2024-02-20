package evgeniy.ryzhikov.database_module.data

import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

interface FavoriteRepository {
    suspend fun addToFavorites(imageInfo: ImageInfoEntity)

    suspend fun removeFromFavorites(uuid: String)

    suspend fun getAllFavorites(): List<ImageInfoEntity>
}