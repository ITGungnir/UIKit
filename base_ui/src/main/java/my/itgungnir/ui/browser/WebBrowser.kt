package my.itgungnir.ui.browser

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_web_browser.view.*
import my.itgungnir.ui.R

@SuppressLint("SetJavaScriptEnabled")
class WebBrowser @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var browserView: WebView

    private var progressView: ProgressBar

    private var onErrorCallback: ((Int, String) -> Unit)? = null

    init {

        View.inflate(context, R.layout.view_web_browser, this)

        browserView = webView
        progressView = progressBar

        browserView.apply {
            settings.apply {
                javaScriptEnabled = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
            }
            webViewClient = object : WebViewClient() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }

                @Suppress("OverridingDeprecatedMember")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }

                @RequiresApi(Build.VERSION_CODES.M)
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    error?.let {
                        onErrorCallback?.invoke(it.errorCode, it.description.toString())
                    }
                    super.onReceivedError(view, request, error)
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    progressView.apply {
                        progress = newProgress
                        visibility = when (newProgress) {
                            100 -> View.GONE
                            else -> View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    fun load(url: String, blockImage: Boolean = false): WebBrowser {
        browserView.apply {
            settings.blockNetworkImage = blockImage
            loadUrl(url)
        }
        return this
    }

    fun onError(block: (Int, String) -> Unit) {
        this.onErrorCallback = block
    }
}