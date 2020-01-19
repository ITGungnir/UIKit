package test.itgungnir.ui.banner

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_banner.*
import test.itgungnir.ui.R

/**
 * Description:
 *
 * Created by ITGungnir on 2020-01-19
 */
class BannerActivity : AppCompatActivity() {

    private val bannerItems by lazy { listOf(R.mipmap.img_banner_01, R.mipmap.img_banner_02, R.mipmap.img_banner_03) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        initViews()
        initData()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        // Banner
        banner_banner.bind<Int>(
            layoutId = R.layout.view_banner_item,
            render = { _, view, data ->
                view.findViewById<ImageView>(R.id.view_banner_item).setImageResource(data)
            },
            onClick = { position, _ ->
                Toast.makeText(this, "Click on position $position", Toast.LENGTH_SHORT).show()
            },
            onPageChange = { position, totalCount, _ ->
                banner_indicator.text = "${position + 1}/$totalCount"
            }
        )
    }

    private fun initData() {
        banner_banner.update(bannerItems)
    }
}
