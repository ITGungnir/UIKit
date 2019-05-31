package my.itgungnir.ui.flex

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import my.itgungnir.ui.R
import my.itgungnir.ui.dp2px
import my.itgungnir.ui.easy_adapter.ListItem
import my.itgungnir.ui.easy_adapter.bind
import my.itgungnir.ui.easy_adapter.update

class ScrollableFlexView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var horizontalSpacing: Float = context.dp2px(10.0F)
    private var verticalSpacing: Float = context.dp2px(10.0F)

    private var manager: FlexboxLayoutManager = FlexboxLayoutManager(context).apply {
        flexDirection = FlexDirection.ROW
        flexWrap = FlexWrap.WRAP
    }

    init {
        layoutManager = manager

        context.obtainStyledAttributes(attrs, R.styleable.ScrollableFlexView).apply {
            horizontalSpacing = getDimension(R.styleable.ScrollableFlexView_sfv_horizontalSpacing, horizontalSpacing)
            verticalSpacing = getDimension(R.styleable.ScrollableFlexView_sfv_verticalSpacing, verticalSpacing)
            recycle()
        }

        addItemDecoration(FlexboxItemDecoration(context).apply {
            this.setDrawable(GradientDrawable().apply {
                setSize(horizontalSpacing.toInt(), verticalSpacing.toInt())
            })
            this.setOrientation(FlexboxItemDecoration.BOTH)
        })
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ListItem> bind(layoutId: Int, render: (view: View, data: T) -> Unit) {
        bind(manager = manager).addDelegate(isForViewType = { true }, delegate = FlexDelegate(layoutId, render))
    }

    fun <T : ListItem> refresh(items: List<T>) {
        update(items)
    }
}