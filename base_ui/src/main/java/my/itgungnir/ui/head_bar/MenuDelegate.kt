package my.itgungnir.ui.head_bar

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.view_head_bar_menu_item.view.*
import my.itgungnir.ui.R
import my.itgungnir.ui.easy_adapter.BaseDelegate
import my.itgungnir.ui.easy_adapter.EasyAdapter
import org.jetbrains.anko.textColor

class MenuDelegate(
    private var menuIconColor: Int,
    private var menuTitleColor: Int,
    private var clickCallback: () -> Unit
) : BaseDelegate<MenuItem>() {

    override fun layoutId(): Int = R.layout.view_head_bar_menu_item

    override fun onCreateVH(container: View) {

        container.apply {
            menuIcon.textColor = menuIconColor
            menuTitle.textColor = menuTitleColor
        }
    }

    override fun onBindVH(item: MenuItem, holder: EasyAdapter.VH, position: Int, payloads: MutableList<Bundle>) {

        holder.render(item) {

            this.setOnClickListener {
                item.callback.invoke()
                clickCallback.invoke()
            }

            menuIcon.text = item.iconFont
            menuTitle.text = item.title
        }
    }
}