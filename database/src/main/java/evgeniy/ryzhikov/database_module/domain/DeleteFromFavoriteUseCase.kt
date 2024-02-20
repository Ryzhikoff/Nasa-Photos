package evgeniy.ryzhikov.database_module.domain

import evgeniy.ryzhikov.database_module.data.FavoriteRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend fun execute(uuid: String) {
        favoriteRepository.removeFromFavorites(uuid)
    }
}