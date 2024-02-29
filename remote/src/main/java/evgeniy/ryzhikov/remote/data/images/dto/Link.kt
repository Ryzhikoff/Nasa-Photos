package evgeniy.ryzhikov.remote.data.images.dto


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    val href: String,
    @SerializedName("rel")
    val rel: String,
    @SerializedName("render")
    val render: String?
)