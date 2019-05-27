package my.itgungnir.ui.banner

fun calculateIndex(currPage: Int, totalPage: Int, isInfiniteScroll: Boolean): Int = when (isInfiniteScroll) {
    true -> when (currPage) {
        0 -> totalPage - 3
        totalPage - 1 -> 0
        else -> currPage - 1
    }
    else -> currPage
}