package evgeniy.ryzhikov.remote.data.repository

import evgeniy.ryzhikov.remote.models.ApiResult

interface NasaRepository {
    suspend fun getRandomPhoto(): ApiResult<Any>
}