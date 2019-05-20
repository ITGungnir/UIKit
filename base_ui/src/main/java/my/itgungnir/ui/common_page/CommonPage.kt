package my.itgungnir.ui.common_page

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.view_common_page.view.*
import my.itgungnir.ui.R
import my.itgungnir.ui.status_view.StatusView
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.imageResource

class CommonPage @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CoordinatorLayout(context, attrs, defStyleAttr) {

    private var fabBackColor = Color.WHITE
    private var fabSrc = R.drawable.svg_to_top
    private var fabRippleColor = Color.GRAY

    private var refresh: SwipeRefreshLayout? = null
    private var viewStatus: StatusView? = null

    init {
        View.inflate(context, R.layout.view_common_page, this)

        refresh = refreshLayout
        viewStatus = statusView

        context.obtainStyledAttributes(attrs, R.styleable.CommonPage).apply {
            fabBackColor = getColor(R.styleable.CommonPage_cp_fabBackColor, fabBackColor)
            fabSrc = getResourceId(R.styleable.CommonPage_cp_fabSrc, fabSrc)
            fabRippleColor = getColor(R.styleable.CommonPage_cp_fabRippleColor, fabRippleColor)
            recycle()
        }

        fab.apply {
            backgroundTintList = ColorStateList.valueOf(fabBackColor)
            rippleColor = fabRippleColor
            imageResource = fabSrc
            setOnClickListener {
                when (val view = viewStatus?.getDelegate(StatusView.Status.SUCCEED)) {
                    is RecyclerView -> view.smoothScrollToPosition(0)
                    is ScrollView -> view.smoothScrollTo(0, 0)
                }
            }
        }
    }

    fun refreshLayout() = refresh!!

    fun statusView() = viewStatus!!
}