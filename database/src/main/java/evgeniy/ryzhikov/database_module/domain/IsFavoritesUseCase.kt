package evgeniy.ryzhikov.database_module.domain

import evgeniy.ryzhikov.database_module.data.FavoriteRepository
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity
import javax.inject.Inject

class IsFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend fun execute(entity: ImageInfoEntity): Boolean =
        repository.isFavorites(entity)
}