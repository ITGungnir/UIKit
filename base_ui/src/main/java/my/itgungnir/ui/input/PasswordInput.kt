package my.itgungnir.ui.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.view_password_input.view.*
import my.itgungnir.ui.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.textColor

@SuppressLint("CheckResult")
class PasswordInput @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var hintStr: String = ""
    private var hintColor: Int = Color.LTGRAY
    private var textColor: Int = Color.DKGRAY
    private var showIcon: String = ""
    private var hideIcon: String = ""
    private var iconColor: Int = Color.DKGRAY
    private var dividerColor: Int = Color.LTGRAY

    private var editText: EditText? = null

    init {
        View.inflate(context, R.layout.view_password_input, this)

        editText = password

        context.obtainStyledAttributes(attrs, R.styleable.PasswordInput).apply {
            hintStr = getString(R.styleable.PasswordInput_pi_hint) ?: hintStr
            hintColor = getColor(R.styleable.PasswordInput_pi_hintColor, hintColor)
            textColor = getColor(R.styleable.PasswordInput_pi_textColor, textColor)
            showIcon = getString(R.styleable.PasswordInput_pi_showIcon) ?: showIcon
            hideIcon = getString(R.styleable.PasswordInput_pi_hideIcon) ?: hideIcon
            iconColor = getColor(R.styleable.PasswordInput_pi_iconColor, iconColor)
            dividerColor = getColor(R.styleable.PasswordInput_pi_dividerColor, dividerColor)
            recycle()
        }

        editText?.apply {
            hint = hintStr
            hintTextColor = hintColor
            textColor = this@PasswordInput.textColor
        }
        RxTextView.textChanges(password).subscribe {
            toggle.visibility = if (it.isNullOrBlank()) View.GONE else View.VISIBLE
        }

        toggle.apply {
            text = showIcon
            textColor = iconColor
            setOnClickListener {
                if (toggle.tag == "1") {
                    toggle.tag = "2"
                    toggle.text = hideIcon
                    editText?.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else if (toggle.tag == "2") {
                    toggle.tag = "1"
                    toggle.text = showIcon
                    editText?.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                editText?.setSelection(editText!!.text.length)
            }
        }

        divider.backgroundColor = dividerColor
    }

    fun getInput(): EditText = editText!!
}