package test.itgungnir.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_about_us.*
import test.itgungnir.ui.R

class AboutUsDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.dialog_about_us, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headBar.title("关于我们")
            .back("\uE720") { this.dismiss() }
            .addMenuItem("\ue6df", "增加") {}
            .addMenuItem("\ue6df", "增加") {}
            .addMenuItem("\ue6df", "增加") {}
    }
}