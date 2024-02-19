package evgeniy.ryzhikov.database_module.data

import evgeniy.ryzhikov.database_module.data.dao.ImageInfoDao
import evgeniy.ryzhikov.database_module.data.entity.ImageInfo
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val imageInfoDao: ImageInfoDao) {

    fun addToFavorites(imageInfo: ImageInfo) = imageInfoDao.add(imageInfo)

    fun getAllFavorites(): List<ImageInfo> = imageInfoDao.getAllFavorites()
}
