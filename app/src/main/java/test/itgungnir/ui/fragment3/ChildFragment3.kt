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
import my.itgungnir.ui.browser.WebBrowser
import my.itgungnir.ui.input.ProgressButton
import my.itgungnir.ui.status_view.StatusView
import test.itgungnir.ui.R

class ChildFragment3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_child3, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusView.addDelegate(StatusView.Status.SUCCEED, R.layout.status_view_web) {
            loadSucceedPage(it)
        }.addDelegate(StatusView.Status.FAILED, R.layout.status_view_error) {
            loadFailedPage(it)
        }

        statusView.succeed { }
    }

    @SuppressLint("SetTextI18n")
    private fun loadSucceedPage(view: View) {
        view.findViewById<WebBrowser>(R.id.browser).apply {
            load("https://www.baidu.com/", false)
                .onError { code, msg ->
                    statusView.failed { view ->
                        view.findViewById<TextView>(R.id.errorMsg).text = "$code：$msg"
                        view.findViewById<ProgressButton>(R.id.reload).ready("重新加载")
                    }
                }
            mask(Color.parseColor("#44000000"))
        }
    }

    private fun loadFailedPage(view: View) {
        view.findViewById<ProgressButton>(R.id.reload).apply {
            ready("重新加载")
            setOnClickListener {
                loading()
                statusView.succeed { v -> loadSucceedPage(v) }
            }
        }
    }
}