package my.itgungnir.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.view_simple_dialog.*
import my.itgungnir.ui.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

class SimpleDialog : DialogFragment() {

    private var bgColor: Int = Color.WHITE
    private var msgColor: Int = Color.GRAY
    private var dividerColor: Int = Color.LTGRAY
    private var btnColor: Int = Color.DKGRAY
    private var msg: String = ""
    private var onConfirm: (() -> Unit)? = null
    private var onCancel: (() -> Unit)? = null

    companion object {
        fun newInstance(
            bgColor: Int, msgColor: Int, dividerColor: Int, btnColor: Int, msg: String,
            onConfirm: (() -> Unit)?, onCancel: (() -> Unit)?
        ) = SimpleDialog().apply {
            this.bgColor = bgColor
            this.msgColor = msgColor
            this.dividerColor = dividerColor
            this.btnColor = btnColor
            this.msg = msg
            this.onConfirm = onConfirm
            this.onCancel = onCancel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.view_simple_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.backgroundColor = bgColor

        message.apply {
            text = msg
            textColor = msgColor
        }

        divider1.backgroundColor = dividerColor

        divider2.backgroundColor = dividerColor

        confirm.apply {
            textColor = btnColor
            setOnClickListener {
                onConfirm?.invoke()
                dismiss()
            }
        }

        cancel.apply {
            textColor = btnColor
            setOnClickListener {
                onCancel?.invoke()
                dismiss()
            }
        }
    }
}