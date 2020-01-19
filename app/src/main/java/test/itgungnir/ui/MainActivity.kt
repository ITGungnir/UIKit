package test.itgungnir.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import test.itgungnir.ui.banner.BannerActivity
import test.itgungnir.ui.rich_text.RichTextActivity
import test.itgungnir.ui.status_view.StatusViewActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 轮播图控件 - Banner
        btn_banner.setOnClickListener {
            navigate(BannerActivity::class.java)
        }

        // 富文本控件 - RichText
        btn_rich_text.setOnClickListener {
            navigate(RichTextActivity::class.java)
        }

        // 状态切换控件 - StatusView
        btn_status_view.setOnClickListener {
            navigate(StatusViewActivity::class.java)
        }
    }

    private fun navigate(clz: Class<out Activity>) {
        startActivity(Intent(this, clz))
    }
}
