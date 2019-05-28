package my.itgungnir.ui.head_bar

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.View.OVER_SCROLL_NEVER
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import my.itgungnir.ui.easy_adapter.EasyAdapter
import my.itgungnir.ui.easy_adapter.bind
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.wrapContent

/**
 * 参考：https://blog.csdn.net/qq_30552993/article/details/70141315
 */
class HeadBarMenu(
    context: Context,
    menuBackground: Int,
    menuIconColor: Int,
    menuTitleColor: Int
) {

    private var menuList: RecyclerView = RecyclerView(context)
    private var menuWindow: PopupWindow? = null

    private var listAdapter: EasyAdapter? = null

    init {
        menuList.apply {
            backgroundColor = menuBackground
            overScrollMode = OVER_SCROLL_NEVER
        }
        listAdapter = menuList.bind()
            .addDelegate(
                isForViewType = { true },
                delegate = MenuDelegate(
                    menuIconColor = menuIconColor,
                    menuTitleColor = menuTitleColor,
                    clickCallback = { menuWindow?.dismiss() }
                )
            )
            .initialize()

        menuWindow = PopupWindow(menuList, wrapContent, wrapContent, true).apply {
            setBackgroundDrawable(ColorDrawable(0x00000000))
        }
    }

    fun setItems(items: List<MenuItem>) {
        listAdapter?.update(items)
    }

    fun showUnder(anchor: View) {
        menuWindow?.showAsDropDown(anchor)
    }
}