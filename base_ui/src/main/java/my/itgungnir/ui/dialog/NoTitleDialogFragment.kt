package my.itgungnir.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment

open class NoTitleDialogFragment : DialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        super.onActivityCreated(savedInstanceState)
    }
}