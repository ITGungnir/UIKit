package test.itgungnir.ui.fragment1

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.list_item_text.view.*
import my.itgungnir.ui.easy_adapter.BaseDelegate
import my.itgungnir.ui.easy_adapter.EasyAdapter
import test.itgungnir.ui.R

class TextDelegate : BaseDelegate<ChildState.TextVO>() {

    override fun layoutId(): Int = R.layout.list_item_text

    override fun onCreateVH(container: View) {}

    override fun onBindVH(
        item: ChildState.TextVO,
        holder: EasyAdapter.VH,
        position: Int,
        payloads: MutableList<Bundle>
    ) {

        holder.render(item) {

            nameView.text = if (payloads.isNotEmpty()) {
                val payload = payloads[0]
                payload.getString("PL_TEXT") ?: item.text
            } else {
                item.text
            }
        }
    }
}