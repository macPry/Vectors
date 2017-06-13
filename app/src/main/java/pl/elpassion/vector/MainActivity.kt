package pl.elpassion.vector

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        (line.drawable as? Animatable)?.start()

        lineView.viewTreeObserver.addOnGlobalLayoutListener {
            lineView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            val parentWidth = (lineView.parent as View).width
            ObjectAnimator.ofFloat(lineView, "translationX", -parentWidth.toFloat(), parentWidth.toFloat()).apply {
                duration = 2000
                repeatCount = INFINITE
                interpolator = AccelerateDecelerateInterpolator()
            }.start()
        }
    }
}