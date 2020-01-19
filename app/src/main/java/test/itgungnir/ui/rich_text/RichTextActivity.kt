package test.itgungnir.ui.rich_text

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_rich_text.*
import my.itgungnir.ui.rich_text.RichText
import test.itgungnir.ui.R

/**
 * Description:
 *
 * Created by ITGungnir on 2020-01-19
 */
class RichTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rich_text)

        initView()
    }

    private fun initView() {
        rich_text.apply {
            // 如果富文本中包含了点击效果，则必须为TextView设置movementMethod
            movementMethod = LinkMovementMethod.getInstance()
            text = RichText()
                .append("正常文本")
                .append("改变背景色")
                .backColor(Color.YELLOW)
                .append("改变前景色")
                .foreColor(Color.BLUE)
                .append("删除线")
                .middleLine()
                .append("下划线")
                .underLine()
                .append("绝对字体")
                .absoluteSize(25)
                .append("相")
                // 设置相对于正常字体大小的变化程度
                .relativeSize(1.5F)
                .append("对")
                .relativeSize(2.0F)
                .append("字")
                .relativeSize(2.5F)
                .append("体")
                .relativeSize(3.0F)
                .append("加粗")
                .bold()
                .append("倾斜")
                .italic()
                .append("可以点击")
                // 第二个参数：设置文本是否有下划线；第三个参数：设置文本点击后是否保留背景色
                .onClick(Color.RED, underLine = true, shadow = false) { toast("点击了可点击的文本") }
                .append("图片")
                .image(context!!, R.mipmap.ic_launcher)
                .create()
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
