package my.itgungnir.ui.list_footer

data class FooterStatus(val status: Status) {

    enum class Status {
        PROGRESSING,
        NO_MORE,
        SUCCEED,
        FAILED
    }
}