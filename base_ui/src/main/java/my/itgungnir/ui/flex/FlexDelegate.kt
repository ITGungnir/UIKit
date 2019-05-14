package my.itgungnir.ui.flex

import android.os.Bundle
import android.view.View
import my.itgungnir.ui.easy_adapter.BaseDelegate
import my.itgungnir.ui.easy_adapter.EasyAdapter
import my.itgungnir.ui.easy_adapter.ListItem

class FlexDelegate<T : ListItem>(val layoutId: Int, val render: (view: View, data: T) -> Unit) : BaseDelegate<T>() {

    override fun layoutId(): Int = layoutId

    override fun onCreateVH(container: View) {
    }

    override fun onBindVH(item: T, holder: EasyAdapter.VH, position: Int, payloads: MutableList<Bundle>) {

        holder.render(item) {
            render.invoke(this, item)
        }
    }
}