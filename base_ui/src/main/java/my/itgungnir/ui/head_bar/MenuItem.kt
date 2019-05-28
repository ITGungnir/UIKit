package my.itgungnir.ui.head_bar

import my.itgungnir.ui.easy_adapter.ListItem

data class MenuItem(
    val iconFont: String,
    val title: String,
    val callback: () -> Unit
) : ListItem