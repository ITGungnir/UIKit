package test.itgungnir.ui.fragment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_child2.*
import org.jetbrains.anko.support.v4.toast
import test.itgungnir.ui.R
import java.util.concurrent.TimeUnit

class ChildFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_child2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressButton.apply {
            ready("提交信息")
            setOnClickListener {
                Single.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading() }
                    .subscribe({
                        ready("提交信息")
                        val s1 = textInput.getInput().editableText.toString().trim()
                        val s2 = shadowInput.getInput().editableText.toString().trim()
                        val s3 = passwordInput.getInput().editableText.toString().trim()
                        toast("$s1-$s2-$s3")
                    }, {
                        ready("提交信息")
                        it.message?.let { msg -> toast(msg) }
                    })
            }
        }
    }
}