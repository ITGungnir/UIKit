package my.itgungnir.ui.common_page

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabBehavior : FloatingActionButton.Behavior {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var currScale = 0F

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        layoutDirection: Int
    ): Boolean {
        child.apply {
            scaleX = currScale
            scaleY = currScale
            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val x = ObjectAnimator.ofFloat(child, "scaleX", currScale, 0.01F)
                    val y = ObjectAnimator.ofFloat(child, "scaleY", currScale, 0.01F)
                    AnimatorSet().apply {
                        play(x).with(y)
                        duration = 250
                    }.start()
                    currScale = 0F
                }
                false
            }
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean = true

    @SuppressLint("RestrictedApi")
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type
        )
        if (dyConsumed >= 0) {
            if (currScale == 1F) {
                val x = ObjectAnimator.ofFloat(child, "scaleX", currScale, 0F)
                val y = ObjectAnimator.ofFloat(child, "scaleY", currScale, 0F)
                AnimatorSet().apply {
                    play(x).with(y)
                    duration = 250
                }.start()
                currScale = 0F
            }
        } else {
            if (currScale == 0F) {
                val x = ObjectAnimator.ofFloat(child, "scaleX", currScale, 1F)
                val y = ObjectAnimator.ofFloat(child, "scaleY", currScale, 1F)
                AnimatorSet().apply {
                    play(x).with(y)
                    duration = 250
                }.start()
                currScale = 1F
            }
        }
    }
}