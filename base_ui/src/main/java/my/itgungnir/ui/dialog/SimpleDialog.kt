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

class SimpleDialog(
    private val bgColor: Int = Color.WHITE,
    private val msgColor: Int = Color.GRAY,
    private val dividerColor: Int = Color.LTGRAY,
    private val btnColor: Int = Color.DKGRAY,
    private val msg: String,
    private val onConfirm: (() -> Unit)? = null,
    private val onCancel: (() -> Unit)? = null
) : DialogFragment() {

    constructor() : this(
        bgColor = Color.WHITE,
        msgColor = Color.GRAY,
        dividerColor = Color.LTGRAY,
        btnColor = Color.DKGRAY,
        msg = "",
        onConfirm = null,
        onCancel = null
    )

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