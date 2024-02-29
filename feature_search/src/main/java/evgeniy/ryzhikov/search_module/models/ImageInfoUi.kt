package evgeniy.ryzhikov.search_module.models

import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.remote.data.images.dto.SearchResultDto


fun SearchResultDto.toListImageInfo(): List<ImageInfoUi> {
    val list = mutableListOf<ImageInfoUi>()
    this.collection.items.forEach {
        list.add(
            ImageInfoUi(
                imageUrl = it.links?.get(0)?.href,
                imageUrlHD = null,
                title = it.data[0].title,
                date = it.data[0].dateCreated,
                description = it.data[0].description,
                isFavorite = false,
                tags = it.data[0].keywords
            )
        )
    }
    return list
}