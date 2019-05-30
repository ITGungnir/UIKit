package my.itgungnir.ui.screen_adapt

import android.app.Application

class ScreenAdapter private constructor() {

    companion object {
        val instance by lazy { ScreenAdapter() }
    }

    fun adapt(application: Application, targetWidth: Float) {
        application.registerActivityLifecycleCallbacks(LifecycleListener(application, targetWidth))
    }
}