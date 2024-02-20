package evgeniy.ryzhikov.database_module.data

import evgeniy.ryzhikov.database_module.data.room.dao.ImageInfoDao
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val imageInfoDao: ImageInfoDao
) : FavoriteRepository {
    override suspend fun addToFavorites(imageInfo: ImageInfoEntity) {
        imageInfoDao.add(imageInfo)
    }

    override suspend fun removeFromFavorites(uuid: String) {
        imageInfoDao.deleteById(uuid)
    }

    override suspend fun getAllFavorites(): List<ImageInfoEntity> =
        imageInfoDao.getAllFavorites()
}