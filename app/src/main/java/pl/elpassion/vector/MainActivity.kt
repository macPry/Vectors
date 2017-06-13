package pl.elpassion.vector

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val lineVectorAnimation by lazy { line.drawable as? Animatable }
    val lineViewAnimation by lazy { AnimatorSet() }
    val fadeOutAnimation by lazy { AnimatorSet() }
    var isFadeOut = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({ init() }, 1000)
        buttonFade.setOnClickListener { fade() }
    }

    fun init() {
        lineVectorAnimation?.start()

        val parentWidth = (lineView.parent as View).width
        val objectAnimatorTranslationX = ObjectAnimator.ofFloat(lineView, "translationX", -parentWidth.toFloat(), parentWidth.toFloat()).apply {
            repeatCount = INFINITE
            interpolator = AccelerateDecelerateInterpolator()
        }
        val objectAnimatorScaleX = ObjectAnimator.ofFloat(lineView, "scaleX", 1f, 0f).apply {
            repeatCount = INFINITE
        }
        lineViewAnimation.apply {
            playTogether(objectAnimatorTranslationX, objectAnimatorScaleX)
            duration = 1000
        }.start()

        fadeOutAnimation.apply {
            playTogether(ObjectAnimator.ofFloat(line, "alpha", 1f, 0f), ObjectAnimator.ofFloat(lineView, "alpha", 1f, 0f))
            duration = 1000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    lineVectorAnimation?.stop()
                    line.visibility = View.GONE
                    lineViewAnimation.end()
                    lineView.visibility = View.GONE
                }
            })
        }
    }

    fun fade() {
        if (isFadeOut) {
            line.visibility = View.VISIBLE
            line.alpha = 1f
            lineView.visibility = View.VISIBLE
            lineView.alpha = 1f
            lineViewAnimation.start()
            lineVectorAnimation?.start()
        } else {
            fadeOutAnimation.start()
        }
        isFadeOut = !isFadeOut
    }
}