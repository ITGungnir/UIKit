package test.itgungnir.ui.main

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import my.itgungnir.ui.dialog.SimpleDialog
import my.itgungnir.ui.icon_font.IconFontView
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import test.itgungnir.ui.*
import test.itgungnir.ui.fragment1.ChildFragment1
import test.itgungnir.ui.fragment2.ChildFragment2
import test.itgungnir.ui.fragment3.ChildFragment3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    private fun initComponent() {

        headBar.title("Test UIKit")
            .back("\ue720") { finish() }
            .addToolButton("\ue834") {
                SimpleDialog.newInstance(
                    bgColor = Color.parseColor(COLOR_DIALOG_BG),
                    msgColor = Color.parseColor(COLOR_DIALOG_MSG),
                    dividerColor = Color.parseColor(COLOR_DIVIDER),
                    btnColor = Color.parseColor(COLOR_DIALOG_BTN),
                    msg = "Simple Dialog Test for UIKit.",
                    onConfirm = { toast("Confirm") },
                    onCancel = { toast("Cancel") }
                ).show(supportFragmentManager, SimpleDialog::class.java.name)
            }
            .addToolButton("\ue833") {
                SearchDialog().show(supportFragmentManager, SearchDialog::class.java.name)
            }

        val selectedColor = Color.parseColor(COLOR_ICON_SELECT)
        val unSelectedColor = Color.parseColor(COLOR_DIVIDER)

        bottomBar.init(
            targetFrameId = R.id.fragments,
            fragmentManager = supportFragmentManager,
            items = listOf(
                TabItem("Frag1", "\uE703", "\uE702") to ChildFragment1(),
                TabItem("Frag2", "\uE6EC", "\uE6EB") to ChildFragment2(),
                TabItem("Frag3", "\uE6EF", "\uE6EE") to ChildFragment3()
            ),
            itemLayoutId = R.layout.list_item_main_bottom_tab,
            render = { view, data, selected ->
                val icon = view.findViewById<IconFontView>(R.id.iconView)
                val title = view.findViewById<TextView>(R.id.titleView)
                title.text = data.title
                when (selected) {
                    true -> {
                        icon.text = data.selectedIcon
                        icon.textColor = selectedColor
                        title.textColor = selectedColor
                    }
                    false -> {
                        icon.text = data.unselectedIcon
                        icon.textColor = unSelectedColor
                        title.textColor = unSelectedColor
                    }
                }
            }
        )
    }

    private data class TabItem(
        val title: String,
        val unselectedIcon: String,
        val selectedIcon: String
    )
}