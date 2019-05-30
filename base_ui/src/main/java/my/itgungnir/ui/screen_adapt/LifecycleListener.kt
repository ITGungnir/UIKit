package my.itgungnir.ui.screen_adapt

import android.app.Activity
import android.app.Application
import android.content.res.Resources
import android.os.Bundle

class LifecycleListener(private val application: Application, private val targetWidth: Float) :
    Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            val systemDM = Resources.getSystem().displayMetrics
            val appDM = application.resources.displayMetrics
            val activityDM = it.resources.displayMetrics

            activityDM.apply {
                density = systemDM.widthPixels / targetWidth
                scaledDensity = systemDM.density
                densityDpi = (160 * systemDM.density).toInt()
            }

            appDM.apply {
                density = systemDM.density
                scaledDensity = systemDM.scaledDensity
                densityDpi = systemDM.densityDpi
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}
}