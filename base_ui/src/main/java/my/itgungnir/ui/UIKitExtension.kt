package my.itgungnir.ui

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

/**
 * dp转px
 */
fun Context.dp2px(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

/**
 * 获取XML配置中的颜色
 */
fun Context.color(id: Int) = ContextCompat.getColor(this, id)

/**
 * 隐藏软键盘
 */
fun View.hideSoftInput() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(windowToken, 0)

/**
 * 防抖动的点击事件
 */
fun View.onAntiShakeClick(milliSeconds: Long, block: (View) -> Unit) =
    RxView.clicks(this)
        .throttleFirst(milliSeconds, TimeUnit.MILLISECONDS)
        .subscribe { block.invoke(this) }!!

/**
 * 将HTML代码转换成带样式的字符串
 */
fun html(html: String): String = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()