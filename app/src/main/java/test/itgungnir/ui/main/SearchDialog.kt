package test.itgungnir.ui.main

import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.dialog_search.*
import my.itgungnir.ui.dialog.FullScreenDialog
import my.itgungnir.ui.easy_adapter.ListItem
import org.jetbrains.anko.support.v4.toast
import test.itgungnir.ui.R

class SearchDialog : FullScreenDialog() {

    override fun layoutId(): Int = R.layout.dialog_search

    override fun initComponent() {

        searchBar.back("\ue720") { dismiss() }
            .doOnSearch { toast(it) }

        flexView.bind<FlexVO>(
            layoutId = R.layout.list_item_flex,
            render = { view, data ->
                view.findViewById<Chip>(R.id.tagView).apply {
                    text = data.tagName
                    setOnClickListener {
                        toast(data.tagName)
                    }
                }
            }
        )

        flexView.refresh(initData())
    }

    private fun initData(): List<FlexVO> {
        val list = mutableListOf<FlexVO>()
        for (i in 0..100) {
            val r = (Math.random() * 5 + 1).toInt()
            list.add(FlexVO("Flex".repeat(r)))
        }
        return list
    }

    override fun observeVM() {}

    private data class FlexVO(
        val tagName: String
    ) : ListItem
}