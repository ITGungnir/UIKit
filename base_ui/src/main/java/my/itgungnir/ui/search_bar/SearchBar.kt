package my.itgungnir.ui.search_bar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.view_search_bar.view.*
import my.itgungnir.ui.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class SearchBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var hintStr: String = ""
    private var hintColor: Int = Color.LTGRAY
    private var textColor: Int = Color.DKGRAY
    private var iconFont: String = "🔍"
    private var btnColor: Int = Color.BLACK
    private var dividerColor: Int = Color.LTGRAY

    init {
        View.inflate(context, R.layout.view_search_bar, this)

        context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
            hintStr = getString(R.styleable.SearchBar_sb_hint) ?: hintStr
            hintColor = getColor(R.styleable.SearchBar_sb_hintColor, hintColor)
            textColor = getColor(R.styleable.SearchBar_sb_textColor, textColor)
            iconFont = getString(R.styleable.SearchBar_sb_iconFont) ?: iconFont
            btnColor = getColor(R.styleable.SearchBar_sb_btnColor, btnColor)
            dividerColor = getColor(R.styleable.SearchBar_sb_dividerColor, dividerColor)
            recycle()
        }

        icon.apply {
            text = iconFont
            textColor = this@SearchBar.textColor
        }

        inputBar.apply {
            hint = hintStr
            hintTextColor = hintColor
            textColor = this@SearchBar.textColor
        }

        search.textColor = btnColor

        inputUnderline.backgroundColor = dividerColor

        divider.backgroundColor = dividerColor
    }

    fun back(iconFont: String, block: () -> Unit): SearchBar {
        back.apply {
            text = iconFont
            visibility = View.VISIBLE
            this.textColor = this@SearchBar.textColor
            RxView.clicks(this)
                .throttleFirst(2L, TimeUnit.SECONDS)
                .subscribe { block.invoke() }
        }
        return this
    }

    fun doOnSearch(block: (String) -> Unit): SearchBar {
        RxView.clicks(search)
            .throttleFirst(2L, TimeUnit.SECONDS)
            .subscribe {
                val input = inputBar.editableText.toString().trim()
                block.invoke(input)
            }
        return this
    }

    fun clear() {
        inputBar.setText("")
    }
}