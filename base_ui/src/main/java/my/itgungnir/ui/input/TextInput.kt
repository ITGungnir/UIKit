package my.itgungnir.ui.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.view_text_input.view.*
import my.itgungnir.ui.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor

@SuppressLint("CheckResult")
class TextInput @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var hintStr: String = ""
    private var hintColor: Int = Color.LTGRAY
    private var textColor: Int = Color.DKGRAY
    private var iconFont: String = ""
    private var iconColor: Int = Color.DKGRAY
    private var dividerColor: Int = Color.LTGRAY

    private var editText: EditText? = null

    init {
        View.inflate(context, R.layout.view_text_input, this)

        this.editText = inputView

        context.obtainStyledAttributes(attrs, R.styleable.TextInput).apply {
            hintStr = getString(R.styleable.TextInput_ti_hint) ?: hintStr
            hintColor = getColor(R.styleable.TextInput_ti_hintColor, hintColor)
            textColor = getColor(R.styleable.TextInput_ti_textColor, textColor)
            iconFont = getString(R.styleable.TextInput_ti_iconFont) ?: iconFont
            iconColor = getColor(R.styleable.TextInput_ti_iconColor, iconColor)
            dividerColor = getColor(R.styleable.TextInput_ti_dividerColor, dividerColor)
            recycle()
        }

        editText?.apply {
            hint = hintStr
            hintTextColor = hintColor
            textColor = this@TextInput.textColor
        }
        RxTextView.textChanges(inputView).subscribe {
            clear.visibility = if (it.isNullOrBlank()) View.GONE else View.VISIBLE
        }

        clear.apply {
            text = iconFont
            textColor = iconColor
            setOnClickListener {
                editText?.setText("")
            }
        }

        divider.backgroundColor = dividerColor
    }

    fun getInput(): EditText = editText!!
}