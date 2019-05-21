package test.itgungnir.ui.fragment3

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_child3.*
import my.itgungnir.ui.input.ProgressButton
import org.jetbrains.anko.support.v4.toast
import test.itgungnir.ui.R

class ChildFragment3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_child3, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.apply {
            ready("Go Back")
            setOnClickListener {
                if (!browserView.goBack()) {
                    toast("Back stack empty.")
                }
            }
        }

        browserView.load(
            url = "https://bugly.qq.com/v2/index",
            blockImage = false,
            indicatorColor = Color.YELLOW,
            errorLayoutId = R.layout.status_view_error,
            errorCallback = {
                it.findViewById<TextView>(R.id.errorMsg).text = "页面加载时出现问题，请重试~"
                it.findViewById<ProgressButton>(R.id.reload).ready("重新加载")
            }
        )
    }
}