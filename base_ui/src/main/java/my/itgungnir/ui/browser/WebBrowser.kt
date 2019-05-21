package my.itgungnir.ui.browser

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.view_web_browser.view.*
import my.itgungnir.ui.R

@SuppressLint("SetJavaScriptEnabled")
class WebBrowser @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var containerView: FrameLayout

    private lateinit var agentWeb: AgentWeb

    init {

        View.inflate(context, R.layout.view_web_browser, this)

        this.containerView = webContainer
    }

    fun load(url: String, blockImage: Boolean = false, errorLayoutId: Int, errorCallback: (View) -> Unit) {

        val errorView = LayoutInflater.from(context).inflate(errorLayoutId, this, false).apply {
            errorCallback.invoke(this)
        }

        agentWeb = AgentWeb.with(context as Activity)
            .setAgentWebParent(webContainer, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setMainFrameErrorView(errorView)
            .createAgentWeb()
            .ready()
            .go(url)

        agentWeb.webCreator.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                useWideViewPort = true
                loadWithOverviewMode = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
                blockNetworkImage = blockImage
            }
        }
    }

    fun goBack() = agentWeb.back()
}