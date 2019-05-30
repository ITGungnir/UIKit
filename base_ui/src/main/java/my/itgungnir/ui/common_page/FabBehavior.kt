package my.itgungnir.ui.common_page

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabBehavior : FloatingActionButton.Behavior {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        layoutDirection: Int
    ): Boolean {
        child.apply {
            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP && child.visibility == View.VISIBLE) {
                    animateOut(child)
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

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        type: Int
    ) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)

        if (target is RecyclerView) {
            val manager = target.layoutManager
            if (manager is LinearLayoutManager) {
                if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                    animateOut(child)
                }
            } else if (manager is GridLayoutManager) {
                if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                    animateOut(child)
                }
            } else if (manager is StaggeredGridLayoutManager) {
                var array = IntArray(manager.spanCount)
                array = manager.findFirstCompletelyVisibleItemPositions(array)
                if (array[0] == 0) {
                    animateOut(child)
                }
            }
        } else if (target is ScrollView) {
            if (target.y == 0F) {
                animateOut(child)
            }
        }
    }

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

        if (dyConsumed >= 0 && child.visibility == View.VISIBLE) {
            animateOut(child)
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            animateIn(child)
        }
    }

    private fun animateIn(child: FloatingActionButton) {
        val x = ObjectAnimator.ofFloat(child, "scaleX", 0F, 1F)
        val y = ObjectAnimator.ofFloat(child, "scaleY", 0F, 1F)
        AnimatorSet().apply {
            play(x).with(y)
            duration = 100
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                @SuppressLint("RestrictedApi")
                override fun onAnimationEnd(animation: Animator?) {
                    child.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }.start()
    }

    private fun animateOut(child: FloatingActionButton) {
        val x = ObjectAnimator.ofFloat(child, "scaleX", 1F, 0F)
        val y = ObjectAnimator.ofFloat(child, "scaleY", 1F, 0F)
        AnimatorSet().apply {
            play(x).with(y)
            duration = 100
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                @SuppressLint("RestrictedApi")
                override fun onAnimationEnd(animation: Animator?) {
                    child.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }.start()
    }
}