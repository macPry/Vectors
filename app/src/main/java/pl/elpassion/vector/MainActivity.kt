package pl.elpassion.vector

import android.animation.AnimatorInflater
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (image.drawable as? Animatable)?.start()
        (line.drawable as? Animatable)?.start()

        lineView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        val objectAnimator = AnimatorInflater.loadAnimator(this, R.animator.line_view_animator)
        objectAnimator.setTarget(lineView)
        objectAnimator.start()
    }

}

