package my.itgungnir.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.ColorInt
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.view_simple_dialog.*
import my.itgungnir.ui.R
import my.itgungnir.ui.dp2px
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.textColor

class SimpleDialog private constructor() : DialogFragment() {

    private lateinit var param: SimpleDialogParam

    companion object {
        private fun newInstance(param: SimpleDialogParam) = SimpleDialog().apply {
            this.param = param
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.view_simple_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.backgroundDrawable = GradientDrawable().apply {
            setColor(param.backgroundColor)
            context?.dp2px(param.cornerRadius)?.let { cornerRadius = it }
        }

        if (param.title.isNullOrBlank()) {
            title.visibility = View.GONE
        } else {
            title.apply {
                text = param.title
                textColor = param.titleColor
            }
        }

        message.apply {
            text = param.message
            textColor = param.messageColor
        }

        listOf(divider1, divider2).forEach {
            it.backgroundColor = param.dividerColor
        }

        confirm.apply {
            text = param.confirmText
            textColor = param.confirmColor
            setOnClickListener {
                param.confirmCallback?.invoke()
                dismissAllowingStateLoss()
            }
        }

        cancel.apply {
            text = param.cancelText
            textColor = param.cancelColor
            setOnClickListener {
                param.cancelCallback?.invoke()
                dismissAllowingStateLoss()
            }
        }
    }

    class Builder {

        private var parameter: SimpleDialogParam = SimpleDialogParam()

        fun backgroundColor(@ColorInt color: Int, cornerRadius: Float? = null) = apply {
            parameter.backgroundColor = color
            cornerRadius?.let { parameter.cornerRadius = it }
        }

        fun dividerColor(@ColorInt color: Int) = apply {
            parameter.dividerColor = color
        }

        fun title(text: String, @ColorInt color: Int? = null) = apply {
            parameter.apply {
                this.title = text
                color?.let { this.titleColor = it }
            }
        }

        fun message(text: String, @ColorInt color: Int? = null) = apply {
            parameter.apply {
                this.message = text
                color?.let { this.messageColor = it }
            }
        }

        fun confirm(
            text: String? = null,
            @ColorInt color: Int? = null,
            callback: (() -> Unit)? = null
        ) = apply {
            parameter.apply {
                text?.let { this.confirmText = it }
                color?.let { this.confirmColor = it }
                callback?.let { confirmCallback = it }
            }
        }

        fun cancel(
            text: String? = null,
            @ColorInt color: Int? = null,
            callback: (() -> Unit)? = null
        ) = apply {
            parameter.apply {
                text?.let { this.cancelText = it }
                color?.let { this.cancelColor = it }
                callback?.let { cancelCallback = it }
            }
        }

        fun create() = newInstance(parameter)
    }

}