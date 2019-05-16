package test.itgungnir.ui.fragment2

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_child2.*
import my.itgungnir.ui.rich_text.RichText
import org.jetbrains.anko.support.v4.toast
import test.itgungnir.ui.R
import java.util.concurrent.TimeUnit

class ChildFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_child2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressButton.apply {
            ready("提交信息")
            setOnClickListener {
                Single.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading() }
                    .subscribe({
                        ready("提交信息")
                        val s1 = textInput.getInput().editableText.toString().trim()
                        val s2 = shadowInput.getInput().editableText.toString().trim()
                        val s3 = passwordInput.getInput().editableText.toString().trim()
                        toast("$s1-$s2-$s3")
                    }, {
                        ready("提交信息")
                        it.message?.let { msg -> toast(msg) }
                    })
            }
        }

        richText.apply {
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
                .onClick(Color.RED, true, false) { toast("点击了可点击的文本") }
                .append("图片")
                .image(context!!, R.mipmap.ic_launcher)
                .create()
        }
    }
}