package evgeniy.ryzhikov.remote.data.images.repository

import evgeniy.ryzhikov.remote.models.ApiResult

interface SearchRepository {

    suspend fun search(query: String, page: Int, pageSize: Int): ApiResult<Any>
}