package evgeniy.ryzhikov.database_module.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import evgeniy.ryzhikov.database_module.data.TABLE_NAME_IMAGE_INFO

@Entity(
    tableName = TABLE_NAME_IMAGE_INFO,
    indices = [Index(
        value = ["title", "link"],
        unique = true,
    )]
)
data class ImageInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "link_uhd") val linkUhd: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "tags", defaultValue = "") val tags: String?,
)