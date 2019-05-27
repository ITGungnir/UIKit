package test.itgungnir.ui.fragment1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item_banner.view.*
import my.itgungnir.ui.easy_adapter.BaseDelegate
import my.itgungnir.ui.easy_adapter.EasyAdapter
import org.jetbrains.anko.imageResource
import test.itgungnir.ui.R

class BannerDelegate : BaseDelegate<ChildState.BannerVO>() {

    override fun layoutId(): Int = R.layout.list_item_banner

    @SuppressLint("SetTextI18n")
    override fun onCreateVH(container: View) {
        container.apply {
            banner.bind<Int>(
                layoutId = R.layout.list_item_banner_child,
                render = { _, view, data ->
                    view.findViewById<ImageView>(R.id.imageView).imageResource = data
                },
                onClick = { position, _ ->
                    Toast.makeText(this.context, "Click on position $position", Toast.LENGTH_SHORT).show()
                },
                onPageChange = { position, totalCount, _ ->
                    indicator.text = "${position + 1}/$totalCount"
                }
            )
        }
    }

    override fun onBindVH(
        item: ChildState.BannerVO,
        holder: EasyAdapter.VH,
        position: Int,
        payloads: MutableList<Bundle>
    ) {

        holder.render(item) {

            banner.update(item.imageResources)
        }
    }
}