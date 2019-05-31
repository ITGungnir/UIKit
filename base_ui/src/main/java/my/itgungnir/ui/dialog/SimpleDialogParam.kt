package my.itgungnir.ui.dialog

import android.graphics.Color

data class SimpleDialogParam(
    var backgroundColor: Int = Color.WHITE,
    var cornerRadius: Float = 0F,
    var dividerColor: Int = Color.LTGRAY,
    var title: String? = null,
    var titleColor: Int = Color.BLACK,
    var message: String = "",
    var messageColor: Int = Color.GRAY,
    var confirmText: String = "确定",
    var confirmColor: Int = Color.DKGRAY,
    var confirmCallback: (() -> Unit)? = null,
    var cancelText: String = "取消",
    var cancelColor: Int = Color.DKGRAY,
    var cancelCallback: (() -> Unit)? = null
)