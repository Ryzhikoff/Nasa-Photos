package evgeniy.ryzhikov.core.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SettingProviderImpl @Inject constructor(
    context: Context,
): SettingProvider {

    private val sharePreferences: SharedPreferences =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override var pageSize: Float
        get() = sharePreferences.getFloat(FIELD_PAGE_SIZE, DEFAULT_PAGE_SIZE)
        set(value) {
            saveFloat(FIELD_PAGE_SIZE, value = value)
        }

    override var initialLoadSize: Float
        get() = sharePreferences.getFloat(FILED_INIT_SIZE, DEFAULT_INIT_PAGE_SIZE)
        set(value) {
            saveFloat(FILED_INIT_SIZE, value = value)
        }
    override var bufferSize: Float
        get() = sharePreferences.getFloat(FIELD_BUFFER_SIZE, DEFAULT_BUFFER_SIZE)
        set(value) {
            saveFloat(FIELD_BUFFER_SIZE, value = value)
        }


    private fun saveFloat(field: String, value: Float) {
        sharePreferences.edit {
            putFloat(field, value)
            apply()
        }
    }

    private companion object {
        const val FILE_NAME = "settings"
        const val FIELD_PAGE_SIZE = "page_size"
        const val FILED_INIT_SIZE = "init_size"
        const val FIELD_BUFFER_SIZE = "buffer_size"

        const val DEFAULT_PAGE_SIZE = 20f
        const val DEFAULT_INIT_PAGE_SIZE = 30f
        const val DEFAULT_BUFFER_SIZE = 100f
    }
}