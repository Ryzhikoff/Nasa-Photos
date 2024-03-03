package evgeniy.ryzhikov.remote.domain

import evgeniy.ryzhikov.remote.data.images.repository.SearchRepository
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun execute(query: String, page: Int, pageSize: Int): ApiResult<Any> =
        searchRepository.search(query, page, pageSize)

}