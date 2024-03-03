package evgeniy.ryzhikov.core.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import evgeniy.ryzhikov.core.R

class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    var tagName: String = ""
        private set

    init {
        inflate(context, R.layout.tag_view, this)
    }

    constructor(
        context: Context,
        tagName: String,
    ): this(context) {
        this.tagName = tagName
        findViewById<AppCompatButton>(R.id.name).text = tagName
    }
}