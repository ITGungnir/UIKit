package test.itgungnir.ui

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }
}