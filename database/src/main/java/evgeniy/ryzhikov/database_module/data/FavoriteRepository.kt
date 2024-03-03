package evgeniy.ryzhikov.database_module.data

import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

interface FavoriteRepository {
    suspend fun addToFavorites(imageInfo: ImageInfoEntity)

    suspend fun removeFromFavorites(entity: ImageInfoEntity)

    suspend fun getAllFavorites(): List<ImageInfoEntity>

    suspend fun isFavorites(entity: ImageInfoEntity): Boolean
}