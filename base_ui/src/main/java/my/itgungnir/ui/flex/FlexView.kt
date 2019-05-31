package my.itgungnir.ui.flex

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import my.itgungnir.ui.R
import my.itgungnir.ui.dp2px

class FlexView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FlexboxLayout(context, attrs, defStyleAttr) {

    private var horizontalSpacing: Float = context.dp2px(10.0F)
    private var verticalSpacing: Float = context.dp2px(10.0F)

    private var layoutId: Int? = null

    private var render: ((View, Any) -> Unit)? = null

    private val inflater by lazy { LayoutInflater.from(context) }

    init {
        flexDirection = FlexDirection.ROW
        flexWrap = FlexWrap.WRAP

        context.obtainStyledAttributes(attrs, R.styleable.FlexView).apply {
            horizontalSpacing = getDimension(R.styleable.FlexView_fv_horizontalSpacing, horizontalSpacing)
            verticalSpacing = getDimension(R.styleable.FlexView_fv_verticalSpacing, verticalSpacing)
            recycle()
        }

        setShowDivider(SHOW_DIVIDER_MIDDLE)
        setDividerDrawable(GradientDrawable().apply {
            setSize(horizontalSpacing.toInt(), verticalSpacing.toInt())
        })
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> bind(layoutId: Int, render: (view: View, data: T) -> Unit) {
        this.layoutId = layoutId
        this.render = render as (View, Any) -> Unit
    }

    fun <T> refresh(items: List<T>) {
        if (layoutId == null || render == null) {
            return
        }
        if (childCount > 0) {
            removeAllViews()
        }
        items.forEach {
            val view = inflater.inflate(layoutId!!, this, false)
            render?.invoke(view, it as Any)
            this.addView(view)
        }
    }
}