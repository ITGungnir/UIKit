package test.itgungnir.ui

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary
import my.itgungnir.ui.screen_adapt.ScreenAdapter

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        ScreenAdapter.instance.adapt(this, 375F)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }
}