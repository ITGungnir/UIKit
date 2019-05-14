package my.itgungnir.ui.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.view_shadow_input.view.*
import my.itgungnir.ui.R
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor

@SuppressLint("CheckResult")
class ShadowInput @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var hintStr: String = ""
    private var hintColor: Int = Color.LTGRAY
    private var textColor: Int = Color.DKGRAY
    private var iconFont: String = ""
    private var iconColor: Int = Color.DKGRAY

    private var editText: EditText? = null

    init {

        View.inflate(context, R.layout.view_shadow_input, this)

        this.editText = inputView

        context.obtainStyledAttributes(attrs, R.styleable.ShadowInput).apply {
            hintStr = getString(R.styleable.ShadowInput_si_hint) ?: hintStr
            hintColor = getColor(R.styleable.ShadowInput_si_hintColor, hintColor)
            textColor = getColor(R.styleable.ShadowInput_si_textColor, textColor)
            iconFont = getString(R.styleable.ShadowInput_si_iconFont) ?: iconFont
            iconColor = getColor(R.styleable.ShadowInput_si_iconColor, iconColor)
            recycle()
        }

        editText?.apply {
            hint = hintStr
            hintTextColor = hintColor
            textColor = this@ShadowInput.textColor
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
    }

    fun getInput(): EditText = editText!!
}