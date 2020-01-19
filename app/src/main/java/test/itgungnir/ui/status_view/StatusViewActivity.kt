package test.itgungnir.ui.status_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_status_view.*
import my.itgungnir.ui.status_view.StatusView
import test.itgungnir.ui.R

/**
 * Description:
 *
 * Created by ITGungnir on 2020-01-19
 */
class StatusViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_view)

        initView()
    }

    private fun initView() {
        // StatusView
        status_view
            .addDelegate(StatusView.Status.LOADING, R.layout.view_status_loading) {}
            .addDelegate(StatusView.Status.SUCCEED, R.layout.view_status_succeed) {}
            .addDelegate(StatusView.Status.EMPTY, R.layout.view_status_empty) {}
            .addDelegate(StatusView.Status.FAILED, R.layout.view_status_failed) {}
        // StatusToggle - Loading
        status_loading.setOnClickListener {
            status_view.loading { }
        }
        // StatusToggle - Succeed
        status_success.setOnClickListener {
            status_view.succeed { }
        }
        // StatusToggle - Empty
        status_empty.setOnClickListener {
            status_view.empty { }
        }
        // StatusToggle - Failed
        status_fail.setOnClickListener {
            status_view.failed { }
        }
    }
}
