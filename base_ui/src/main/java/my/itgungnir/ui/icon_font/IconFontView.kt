package my.itgungnir.ui.icon_font

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import my.itgungnir.ui.R

class IconFontView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private var iconFontFace: String = "iconfont.ttf"

    init {

        context.obtainStyledAttributes(attrs, R.styleable.IconFontView).apply {
            iconFontFace = getString(R.styleable.IconFontView_ifv_iconFontFace) ?: iconFontFace
            recycle()
        }

        typeface = Typeface.createFromAsset(context.assets, iconFontFace)
    }
}