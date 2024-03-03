package evgeniy.ryzhikov.remote.models

import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.remote.data.images.dto.SearchItemDto


fun SearchItemDto.toImageInfoUi(): ImageInfoUi =
    ImageInfoUi(
        imageUrl = links?.get(0)?.href,
        imageUrlHD = null,
        title = data[0].title,
        date = data[0].dateCreated,
        description = data[0].description,
        isFavorite = false,
        tags = data[0].keywords
    )

