package my.itgungnir.ui.banner

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import my.itgungnir.ui.R
import java.util.concurrent.TimeUnit

class Banner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val manager: LinearLayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private var scrollTimeSpan: Long = 5L
    private var autoScroll: Boolean = true
    private var infiniteScroll: Boolean = true

    private var disposable: Disposable? = null

    private var currPage: Int = 1

    private var items: MutableList<Any> = mutableListOf()

    private var onPageChange: ((position: Int, totalCount: Int, data: Any) -> Unit)? = null

    private var bannerAdapter: BannerAdapter? = null

    init {
        layoutManager = manager

        PagerSnapHelper().attachToRecyclerView(this)

        context.obtainStyledAttributes(attrs, R.styleable.Banner).apply {
            scrollTimeSpan = getInteger(R.styleable.Banner_b_scrollTimeSpan, scrollTimeSpan.toInt()).toLong()
            autoScroll = getBoolean(R.styleable.Banner_b_autoScroll, autoScroll)
            infiniteScroll = getBoolean(R.styleable.Banner_b_infiniteScroll, infiniteScroll)
            recycle()
        }

        // 如果不是无限滚动，则不自动滚动
        if (!infiniteScroll) {
            autoScroll = false
        }

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        val currIndex = manager.findFirstCompletelyVisibleItemPosition()
                        if (infiniteScroll) {
                            currPage = when {
                                currIndex > items.size - 2 -> 1
                                currIndex < 0 -> currPage
                                currIndex < 1 -> items.size - 2
                                else -> currIndex
                            }
                            if (currPage == items.size - 2 && currIndex == 0) {
                                this@Banner.scrollToPosition(items.size - 2)
                            } else if (currPage == 1 && currIndex == items.size - 1) {
                                this@Banner.scrollToPosition(1)
                            } else {
                                this@Banner.smoothScrollToPosition(currPage)
                            }
                            initDisposable()
                        } else {
                            currPage = currIndex
                            this@Banner.smoothScrollToPosition(currPage)
                        }
                        invokePageChangeListener()
                    }
                    else -> recycleDisposable()
                }
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        recycleDisposable()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        when (visibility) {
            View.VISIBLE -> initDisposable()
            View.GONE, View.INVISIBLE -> recycleDisposable()
        }
    }

    private fun initDisposable() {
        recycleDisposable()
        if (autoScroll) {
            this.disposable = Observable.timer(scrollTimeSpan, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    this.smoothScrollToPosition(++currPage)
                }
        }
    }

    private fun recycleDisposable() {
        if (disposable?.isDisposed == false) {
            this.disposable?.dispose()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <E> bind(
        layoutId: Int,
        render: (position: Int, view: View, data: E) -> Unit,
        onClick: (position: Int, data: E) -> Unit,
        onPageChange: (position: Int, totalCount: Int, data: E) -> Unit
    ) {
        this.onPageChange = onPageChange as (Int, Int, Any) -> Unit
        this.bannerAdapter = BannerAdapter(
            layoutId = layoutId,
            isInfiniteScroll = infiniteScroll,
            render = render as (Int, View, Any) -> Unit,
            onClick = onClick as (Int, Any) -> Unit
        )
        this.adapter = bannerAdapter
    }

    @Suppress("UNCHECKED_CAST")
    fun <E> update(items: List<E>) {
        if (items.isEmpty()) {
            return
        }
        this.items.clear()
        if (infiniteScroll) {
            this.items.add(items[items.size - 1] as Any)
        }
        this.items.addAll(items as List<Any>)
        if (infiniteScroll) {
            this.items.add(items[0])
        }
        this.bannerAdapter?.update(this.items)
        Handler().post {
            currPage = if (infiniteScroll) 1 else 0
            this.scrollToPosition(currPage)
            initDisposable()
            invokePageChangeListener()
        }
    }

    private fun invokePageChangeListener() {
        val realPosition = calculateIndex(currPage, items.size, infiniteScroll)
        this.onPageChange?.invoke(realPosition, if (infiniteScroll) items.size - 2 else items.size, items[currPage])
    }
}