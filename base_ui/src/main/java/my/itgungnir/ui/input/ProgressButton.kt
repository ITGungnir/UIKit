package my.itgungnir.ui.input

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_progress_button.view.*
import my.itgungnir.ui.R
import my.itgungnir.ui.dp2px
import org.jetbrains.anko.backgroundDrawable

/**
 * 带进度条的按钮
 */
class ProgressButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var enabledBg: GradientDrawable
    private var disabledBg: GradientDrawable

    private var enabledColor: Int = Color.DKGRAY
    private var disabledColor: Int = Color.LTGRAY
    private var enabledTextColor: Int = Color.WHITE
    private var disabledTextColor: Int = Color.DKGRAY
    private var cornerRadius: Float = context.dp2px(3F)

    init {

        setWillNotDraw(false)

        View.inflate(context, R.layout.view_progress_button, this)

        context.obtainStyledAttributes(attrs, R.styleable.ProgressButton).apply {
            enabledColor = getColor(R.styleable.ProgressButton_pb_enabledColor, enabledColor)
            disabledColor = getColor(R.styleable.ProgressButton_pb_disabledColor, disabledColor)
            enabledTextColor = getColor(R.styleable.ProgressButton_pb_enabledTextColor, enabledTextColor)
            disabledTextColor = getColor(R.styleable.ProgressButton_pb_disabledTextColor, disabledTextColor)
            cornerRadius = getDimension(R.styleable.ProgressButton_pb_cornerRadius, cornerRadius)
            recycle()
        }

        enabledBg = GradientDrawable().apply {
            setColor(enabledColor)
            cornerRadius = this@ProgressButton.cornerRadius
            callback = this@ProgressButton
        }

        disabledBg = GradientDrawable().apply {
            cornerRadius = this@ProgressButton.cornerRadius
            setColor(disabledColor)
        }
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (isEnabled && isPressed) {
            enabledBg.alpha = (255 * 0.6).toInt()
        } else {
            enabledBg.alpha = 255
        }
    }

    fun disabled(tip: String) {
        setEnableFlag(false)
        progressBar.visibility = View.GONE
        title.apply {
            visibility = View.VISIBLE
            text = tip
        }
    }

    fun ready(tip: String) {
        setEnableFlag(true)
        progressBar.visibility = View.GONE
        title.apply {
            visibility = View.VISIBLE
            text = tip
        }
    }

    fun loading() {
        setEnableFlag(false)
        progressBar.visibility = View.VISIBLE
        title.visibility = View.GONE
    }

    private fun setEnableFlag(flag: Boolean) {
        isEnabled = flag
        title.setTextColor(if (flag) enabledTextColor else disabledTextColor)
        backgroundDrawable = if (flag) enabledBg else disabledBg
    }
}