/*
 * Copyright WeiLianYang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.william.gradient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.william.gradient.databinding.ActivitySecondBinding

/**
 * @author William
 * @date 2022/1/9 15:05
 * Class Comment：
 */
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val silentTextView1 = createGradientTextView(
            "Gradient TextView topToBottom",
            GradientTextView.topToBottom,
            animate = false
        )
        binding.root.addView(silentTextView1)

        val silentTextView2 = createGradientTextView(
            "Gradient TextView leftToRight",
            GradientTextView.leftToRight,
            animate = false
        )
        binding.root.addView(silentTextView2)

        val slowTextView = createGradientTextView(
            "Gradient TextView Speed: Slow",
            GradientTextView.leftToRight,
            GradientTextView.slow
        )
        binding.root.addView(slowTextView)

        val normalTextView = createGradientTextView(
            "Gradient TextView Speed: Normal",
            GradientTextView.leftToRight,
            GradientTextView.normal
        )
        binding.root.addView(normalTextView)

        val fastTextView = createGradientTextView(
            "Gradient TextView Speed: Fast",
            GradientTextView.leftToRight,
            GradientTextView.fast
        )
        binding.root.addView(fastTextView)

    }

    private var topY = 150

    private fun createGradientTextView(
        text: String,
        @AnimateDirection direction: Int,
        @TranslateSpeed speed: Int = 0,
        animate: Boolean = true
    ): GradientTextView {

        topY += 150

        return GradientTextView(this).apply {
            layoutParams =
                ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 100)
                    .apply {
                        topToTop = ConstraintLayout.LayoutParams.PARENT_ID

                        topMargin = topY
                        leftMargin = 100
                    }
            this.text = text
            this.textSize = 18f

            this.direction = direction
            this.translateSpeed = speed
            this.translateAnimate = animate

            setColor(
                ContextCompat.getColor(this@SecondActivity, R.color.color_03DAC5),
                ContextCompat.getColor(this@SecondActivity, R.color.color_6200EE)
            )
        }
    }
}