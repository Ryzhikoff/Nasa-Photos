package evgeniy.ryzhikov.remote.data.images.dto


import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("collection")
    val collection: Collection
)