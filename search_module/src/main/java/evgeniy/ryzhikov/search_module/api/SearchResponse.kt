package evgeniy.ryzhikov.search_module.api


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("collection")
    val collection: Collection
)