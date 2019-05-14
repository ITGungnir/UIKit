package test.itgungnir.ui.fragment1

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel
import my.itgungnir.ui.easy_adapter.ListItem
import test.itgungnir.ui.R
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class ChildViewModel : BaseViewModel<ChildState>(initialState = ChildState()) {

    private var pageNo = 1

    fun getDataList() {
        pageNo = 1
        Single.timer(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                setState {
                    copy(
                        refreshing = true,
                        error = null
                    )
                }
            }
            .subscribe({
                pageNo++
                setState {
                    copy(
                        refreshing = false,
                        items = listOf(
                            ChildState.BannerVO(
                                listOf(
                                    R.mipmap.img_banner_01,
                                    R.mipmap.img_banner_02,
                                    R.mipmap.img_banner_03
                                )
                            )
                        ) + newList(),
                        hasMore = true,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        refreshing = false,
                        error = it
                    )
                }
            })
    }

    fun loadMoreDataList() {
        Single.timer(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                setState {
                    copy(
                        loading = true,
                        error = null
                    )
                }
            }
            .subscribe({
                pageNo++
                setState {
                    copy(
                        loading = false,
                        items = items.toMutableList() + newList(),
                        hasMore = pageNo <= 5,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        loading = false,
                        error = it
                    )
                }
            })
    }

    private fun newList(): List<ListItem> {
        val list = mutableListOf<ListItem>()
        for (i in 0..19) {
            list.add(
                ChildState.TextVO(
                    id = (pageNo - 1) * 20 + i,
                    text = "ItemText".repeat((Math.random() * 10 + 1).toInt())
                )
            )
        }
        return list
    }
}