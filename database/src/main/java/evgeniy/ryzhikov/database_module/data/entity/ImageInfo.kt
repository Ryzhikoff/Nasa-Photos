package evgeniy.ryzhikov.database_module.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import evgeniy.ryzhikov.database_module.data.TABLE_NAME_IMAGE_INFO

@Entity(
    tableName = TABLE_NAME_IMAGE_INFO
)
data class ImageInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "date") val date: String
)