package test.itgungnir.ui.fragment1

import my.itgungnir.rxmvvm.core.mvvm.State
import my.itgungnir.ui.easy_adapter.ListItem

data class ChildState(
    val refreshing: Boolean = false,
    val items: List<ListItem> = listOf(),
    val loading: Boolean = false,
    val hasMore: Boolean = false,
    val error: Throwable? = null
) : State {

    data class BannerVO(
        val imageResources: List<Int>
    ) : ListItem

    data class TextVO(
        val id: Int,
        val text: String
    ) : ListItem
}