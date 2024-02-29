package evgeniy.ryzhikov.remote.data.images.dto


import com.google.gson.annotations.SerializedName

data class SearchItemDto(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("href")
    val href: String,
    @SerializedName("links")
    val links: List<Link>?
)