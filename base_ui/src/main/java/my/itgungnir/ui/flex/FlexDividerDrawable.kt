package my.itgungnir.ui.flex

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue

class FlexDividerDrawable(val context: Context) : GradientDrawable() {

    init {
        setSize(dp2px(10.0F), dp2px(10.0F))
    }

    private fun dp2px(dp: Float): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
}