package test.itgungnir.ui.fragment1

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_child1.*
import my.itgungnir.rxmvvm.core.mvvm.BaseFragment
import my.itgungnir.rxmvvm.core.mvvm.buildFragmentViewModel
import my.itgungnir.ui.easy_adapter.Differ
import my.itgungnir.ui.easy_adapter.EasyAdapter
import my.itgungnir.ui.easy_adapter.ListItem
import my.itgungnir.ui.easy_adapter.bind
import my.itgungnir.ui.list_footer.FooterStatus
import my.itgungnir.ui.list_footer.ListFooter
import my.itgungnir.ui.status_view.StatusView
import org.jetbrains.anko.support.v4.toast
import test.itgungnir.ui.R

class ChildFragment1 : BaseFragment() {

    private var listAdapter: EasyAdapter? = null

    private var footer: ListFooter? = null

    private val viewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = ChildViewModel::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_child1

    override fun initComponent() {
        childPage.apply {
            // Refresh Layout
            refreshLayout().setOnRefreshListener {
                viewModel.getDataList()
            }
            // Status View
            statusView().addDelegate(StatusView.Status.SUCCEED, R.layout.status_view_list) {
                val list = it.findViewById<RecyclerView>(R.id.list)
                // Easy Adapter
                listAdapter = list.bind(
                    diffAnalyzer = object : Differ {
                        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                            if (oldItem is ChildState.BannerVO && newItem is ChildState.BannerVO) {
                                true
                            } else if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO) {
                                oldItem.id == newItem.id
                            } else false

                        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                            if (oldItem is ChildState.BannerVO && newItem is ChildState.BannerVO) {
                                true
                            } else if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO) {
                                oldItem.text == newItem.text
                            } else false

                        override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Bundle? {
                            val bundle = Bundle()
                            if (oldItem is ChildState.TextVO && newItem is ChildState.TextVO && oldItem.text != newItem.text) {
                                bundle.putString("PL_TEXT", newItem.text)
                            }
                            return if (bundle.isEmpty) null else bundle
                        }
                    }).addDelegate({ data -> data is ChildState.BannerVO }, BannerDelegate())
                    .addDelegate({ data -> data is ChildState.TextVO }, TextDelegate())
                    .initialize()
                // List Footer
                footer = ListFooter.Builder()
                    .bindTo(list)
                    .render(R.layout.view_list_footer) { view, status ->
                        val title = view.findViewById<TextView>(R.id.title)
                        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
                        when (status) {
                            FooterStatus.Status.PROGRESSING -> {
                                title.visibility = View.GONE
                                progressBar.visibility = View.VISIBLE
                            }
                            FooterStatus.Status.SUCCEED -> {
                                title.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                title.text = "加载成功，还有更多哦~"
                            }
                            FooterStatus.Status.NO_MORE -> {
                                title.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                title.text = "加载成功，但我是有底线的"
                            }
                            FooterStatus.Status.FAILED -> {
                                title.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                title.text = "啊嘞？怎么出错了！？"
                            }
                        }
                    }
                    .doOnLoadMore {
                        if (!refreshLayout().isRefreshing) {
                            viewModel.loadMoreDataList()
                        }
                    }
                    .build()
            }.succeed { }
        }

        viewModel.getDataList()
    }

    override fun observeVM() {

        viewModel.pick(ChildState::refreshing)
            .observe(this, Observer { refreshing ->
                refreshing?.a?.let {
                    childPage.refreshLayout().isRefreshing = it
                }
            })

        viewModel.pick(ChildState::items, ChildState::hasMore)
            .observe(this, Observer { states ->
                states?.let {
                    when (it.a.isNotEmpty()) {
                        true -> childPage.statusView().succeed { listAdapter?.update(states.a) }
                        else -> Unit
                    }
                    footer?.onLoadSucceed(it.b)
                }
            })

        viewModel.pick(ChildState::loading)
            .observe(this, Observer { loading ->
                if (loading?.a == true) {
                    footer?.onLoading()
                }
            })

        viewModel.pick(ChildState::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                    footer?.onLoadFailed()
                }
            })
    }
}