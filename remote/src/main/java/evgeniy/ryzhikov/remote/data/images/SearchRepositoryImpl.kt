package evgeniy.ryzhikov.remote.data.images

import evgeniy.ryzhikov.remote.data.images.dto.SearchResultDto
import evgeniy.ryzhikov.remote.data.images.repository.SearchRepository
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val imageApi: ImageApi
): SearchRepository {

    override suspend fun search(query: String, page: Int, pageSize: Int): ApiResult<Any> =
        try {
            val response = imageApi.search(query = query, page = page, pageSize = pageSize)
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body() as SearchResultDto)
            } else {
                ApiResult.Error(response.errorBody().toString())
            }

        } catch (e: Exception) {
            ApiResult.Error(e.toString())
        }

}