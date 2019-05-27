package my.itgungnir.ui.list_footer

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("JoinDeclarationAndAssignment")
class ListFooter(
    private val recyclerView: RecyclerView,
    private val loadMore: () -> Unit,
    renderPair: Pair<Int, (View, FooterStatus.Status) -> Unit>? = null
) {

    private var listAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    private var footerAdapter: FooterAdapter

    private var lastVisibleItem = 0

    private var hasMore = false

    private constructor(builder: Builder) : this(builder.recyclerView, builder.listener, builder.renderPair)

    init {
        listAdapter = recyclerView.adapter!!
        footerAdapter = FooterAdapter(listAdapter, FooterStatus.Status.SUCCEED, renderPair)
        listAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() =
                footerAdapter.notifyDataSetChanged()

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) =
                footerAdapter.notifyItemRangeRemoved(positionStart, itemCount)

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) =
                footerAdapter.notifyItemMoved(fromPosition, toPosition)

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) =
                footerAdapter.notifyItemRangeInserted(positionStart, itemCount)

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) =
                footerAdapter.notifyItemRangeChanged(positionStart, itemCount)

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) =
                footerAdapter.notifyItemRangeChanged(positionStart, itemCount, payload)
        })

        applyFooterAdapter()
        applyOnScrollListener()
    }

    private fun applyFooterAdapter() {
        this.recyclerView.adapter = footerAdapter
    }

    private fun applyOnScrollListener() {
        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (hasMore && lastVisibleItem == listAdapter.itemCount) {
                        loadMore()
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when (recyclerView.layoutManager) {
                    is LinearLayoutManager -> lastVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    is GridLayoutManager -> lastVisibleItem =
                        (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                }
                if (dy <= 0) {
                    lastVisibleItem--
                }
            }
        })
    }

    fun onLoading() {
        footerAdapter.notifyStatusChanged(FooterStatus.Status.PROGRESSING)
    }

    fun onLoadSucceed(hasMore: Boolean) {
        this.hasMore = hasMore
        if (hasMore) {
            footerAdapter.notifyStatusChanged(FooterStatus.Status.SUCCEED)
        } else {
            footerAdapter.notifyStatusChanged(FooterStatus.Status.NO_MORE)
        }
    }

    fun onLoadFailed() {
        footerAdapter.notifyStatusChanged(FooterStatus.Status.FAILED)
    }

    class Builder {

        lateinit var recyclerView: RecyclerView
            private set

        var renderPair: Pair<Int, (View, FooterStatus.Status) -> Unit>? = null
            private set

        lateinit var listener: () -> Unit
            private set

        fun bindTo(recyclerView: RecyclerView) = apply {
            this.recyclerView = recyclerView
        }

        fun render(layoutId: Int, statusCallback: (View, FooterStatus.Status) -> Unit) = apply {
            this.renderPair = layoutId to statusCallback
        }

        fun doOnLoadMore(listener: () -> Unit) = apply {
            this.listener = listener
        }

        fun build() = ListFooter(this)
    }
}