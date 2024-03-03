package evgeniy.ryzhikov.remote.data.images.repository

import evgeniy.ryzhikov.remote.data.images.ImagesPageSource

interface SearchPagingRepository {

    fun imagesPagesSource(): ImagesPageSource
}