package my.itgungnir.ui.list_footer

data class FooterStatus(val status: Status) {

    enum class Status(val title: String) {
        PROGRESSING("正在加载..."),
        NO_MORE("没有更多数据了"),
        SUCCEED("加载成功"),
        FAILED("加载失败")
    }
}