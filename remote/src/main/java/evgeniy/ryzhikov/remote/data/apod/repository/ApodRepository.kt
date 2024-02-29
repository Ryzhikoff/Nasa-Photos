package evgeniy.ryzhikov.remote.data.apod.repository

import evgeniy.ryzhikov.remote.models.ApiResult

interface ApodRepository {
    suspend fun getRandomPhoto(): ApiResult<Any>
}