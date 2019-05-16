package my.itgungnir.ui.rich_text.param

import android.view.View

data class ClickParam(
    val textColor: Int,
    val underLine: Boolean,
    val shadow: Boolean,
    val clickCallback: (View) -> Unit
)