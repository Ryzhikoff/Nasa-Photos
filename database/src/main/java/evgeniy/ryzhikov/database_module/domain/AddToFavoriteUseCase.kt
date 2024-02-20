package evgeniy.ryzhikov.database_module.domain

import evgeniy.ryzhikov.database_module.data.FavoriteRepository
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend fun execute(imageInfoEntity: ImageInfoEntity) {
        favoriteRepository.addToFavorites(imageInfoEntity)
    }
}