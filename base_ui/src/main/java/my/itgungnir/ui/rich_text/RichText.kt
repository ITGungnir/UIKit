package my.itgungnir.ui.rich_text

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.*
import android.view.View
import android.widget.TextView
import my.itgungnir.ui.rich_text.param.ClickParam
import my.itgungnir.ui.rich_text.param.ImageParam

/**
 * https://www.jianshu.com/p/f004300c6920
 */
class RichText {

    private var content: CharSequence = ""

    private var flags: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE

    private val spannableStringBuilder = SpannableStringBuilder()

    private var backColor: Int? = null
    private var foreColor: Int? = null
    private var middleLine: Boolean = false
    private var underLine: Boolean = false
    private var absoluteSize: Int? = null
    private var relativeSize: Float? = null
    private var bold: Boolean = false
    private var italic: Boolean = false
    private var onClick: ClickParam? = null
    private var image: ImageParam? = null

    private fun setDefault() {
        flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        backColor = null
        foreColor = null
        middleLine = false
        underLine = false
        absoluteSize = null
        relativeSize = null
        bold = false
        italic = false
        onClick = null
        image = null
    }

    private fun applyLast() {
        update()
        setDefault()
    }

    // 追加字符串
    fun append(content: CharSequence): RichText = apply {
        applyLast()
        this.content = content
    }

    // 背景色
    fun backColor(color: Int): RichText = apply {
        this.backColor = color
    }

    // 前景色
    fun foreColor(color: Int): RichText = apply {
        this.foreColor = color
    }

    // 删除线
    fun middleLine(): RichText = apply {
        this.middleLine = true
    }

    // 下划线
    fun underLine(): RichText = apply {
        this.underLine = true
    }

    // 绝对字体
    fun absoluteSize(size: Int): RichText = apply {
        this.absoluteSize = size
    }

    // 相对字体
    fun relativeSize(size: Float): RichText = apply {
        this.relativeSize = size
    }

    // 加粗
    fun bold(): RichText = apply {
        this.bold = true
    }

    // 斜体
    fun italic(): RichText = apply {
        this.italic = true
    }

    // 点击事件
    fun onClick(textColor: Int, underLine: Boolean, shadow: Boolean, clickCallback: (View) -> Unit): RichText =
        apply {
            this.onClick = ClickParam(textColor, underLine, shadow, clickCallback)
        }

    // 添加图片
    fun image(context: Context, imgRes: Int): RichText = apply {
        this.image = ImageParam(context, imgRes)
    }

    // 生成字符串
    fun create(): CharSequence {
        applyLast()
        return spannableStringBuilder
    }

    private fun update() {
        if (content.isEmpty()) {
            return
        }
        val start = spannableStringBuilder.length
        spannableStringBuilder.append(content)
        val end = spannableStringBuilder.length

        backColor?.let {
            spannableStringBuilder.setSpan(BackgroundColorSpan(it), start, end, flags)
        }

        foreColor?.let {
            spannableStringBuilder.setSpan(ForegroundColorSpan(it), start, end, flags)
        }

        if (middleLine) {
            spannableStringBuilder.setSpan(StrikethroughSpan(), start, end, flags)
        }

        if (underLine) {
            spannableStringBuilder.setSpan(UnderlineSpan(), start, end, flags)
        }

        absoluteSize?.let {
            spannableStringBuilder.setSpan(AbsoluteSizeSpan(it, true), start, end, flags)
        }

        relativeSize?.let {
            spannableStringBuilder.setSpan(RelativeSizeSpan(it), start, end, flags)
        }

        if (bold) {
            spannableStringBuilder.setSpan(StyleSpan(Typeface.BOLD), start, end, flags)
        }

        if (italic) {
            spannableStringBuilder.setSpan(StyleSpan(Typeface.ITALIC), start, end, flags)
        }

        onClick?.let {
            val span = object : ClickableSpan() {
                override fun onClick(view: View) {
                    if (!it.shadow) {
                        (view as TextView).highlightColor = Color.TRANSPARENT
                    }
                    it.clickCallback.invoke(view)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = it.textColor
                    ds.isUnderlineText = it.underLine
                    ds.clearShadowLayer()
                }
            }
            spannableStringBuilder.setSpan(span, start, end, flags)
        }

        image?.let {
            spannableStringBuilder.setSpan(ImageSpan(it.context, it.imgRes), start, end, flags)
        }
    }
}