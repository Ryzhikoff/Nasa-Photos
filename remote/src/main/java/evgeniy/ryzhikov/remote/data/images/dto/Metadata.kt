package evgeniy.ryzhikov.remote.data.images.dto


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("total_hits")
    val totalHits: Int
)