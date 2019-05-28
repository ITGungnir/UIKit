package my.itgungnir.ui.head_bar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.view_head_bar.view.*
import my.itgungnir.ui.R
import my.itgungnir.ui.icon_font.IconFontView
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.leftPadding
import org.jetbrains.anko.textColor
import java.util.concurrent.TimeUnit

class HeadBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private var toolsLayout: LinearLayout? = null
    private var menuView: IconFontView? = null

    private var textColor: Int = Color.WHITE
    private var showDivider: Boolean = false
    private var dividerColor: Int = Color.LTGRAY
    private var menuBackground: Int = Color.WHITE
    private var menuIconColor: Int = Color.BLACK
    private var menuTitleColor: Int = Color.BLACK

    private val menuList = mutableListOf<MenuItem>()

    init {
        View.inflate(context, R.layout.view_head_bar, this)

        context.obtainStyledAttributes(attrs, R.styleable.HeadBar).apply {
            textColor = getColor(R.styleable.HeadBar_hb_textColor, textColor)
            showDivider = getBoolean(R.styleable.HeadBar_hb_showDivider, showDivider)
            dividerColor = getColor(R.styleable.HeadBar_hb_dividerColor, dividerColor)
            menuBackground = getColor(R.styleable.HeadBar_hb_menuBackground, menuBackground)
            menuIconColor = getColor(R.styleable.HeadBar_hb_menuIconColor, menuIconColor)
            menuTitleColor = getColor(R.styleable.HeadBar_hb_menuTitleColor, menuTitleColor)
            recycle()
        }

        back.textColor = textColor

        title.textColor = textColor

        if (showDivider) {
            divider.visibility = View.VISIBLE
            divider.backgroundColor = dividerColor
        } else {
            divider.visibility = View.GONE
        }

        menu.apply {
            this.textColor = this@HeadBar.textColor
            setOnClickListener {
                HeadBarMenu(context, menuBackground, menuIconColor, menuTitleColor).apply {
                    setItems(menuList)
                }.showUnder(menu)
            }
        }

        toolsLayout = tools
        menuView = menu
    }

    fun back(iconFont: String, onBackPressed: () -> Unit): HeadBar {
        back.apply {
            text = iconFont
            visibility = View.VISIBLE
            RxView.clicks(this)
                .throttleFirst(2L, TimeUnit.SECONDS)
                .subscribe { onBackPressed.invoke() }
        }
        title.leftPadding = 0
        return this
    }

    fun title(titleStr: String): HeadBar {
        title.text = titleStr
        return this
    }

    fun addToolButton(iconFont: String, callback: () -> Unit): HeadBar {
        val view = LayoutInflater.from(context).inflate(R.layout.view_head_bar_icon, toolsLayout, false)
        view.findViewById<IconFontView>(R.id.icon).apply {
            text = iconFont
            this.textColor = this@HeadBar.textColor
            RxView.clicks(this)
                .throttleFirst(2L, TimeUnit.SECONDS)
                .subscribe { callback.invoke() }
        }
        toolsLayout?.addView(view)
        return this
    }

    fun addMenuItem(iconFont: String, title: String, callback: () -> Unit): HeadBar {
        menuView?.visibility = View.VISIBLE
        menuList.add(MenuItem(iconFont, title, callback))
        return this
    }

    fun updateToolButton(position: Int, iconFont: String) {
        toolsLayout?.getChildAt(position)?.let {
            (it as IconFontView).text = iconFont
        }
    }

    fun toolButtonCount() = toolsLayout?.childCount ?: 0
}