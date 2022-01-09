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

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView


/**
 * @author William
 * @date 2020/7/5 22:04
 * Class Comment：GradientTextView
 */
class GradientTextView : AppCompatTextView {

    private var mLinearGradient: LinearGradient? = null
    private var mGradientMatrix: Matrix = Matrix()

    @ColorInt
    var startColor = 0

    @ColorInt
    var endColor = 0

    @AnimateDirection
    var direction = 0

    @TranslateSpeed
    var translateSpeed = 0

    var translateAnimate = false

    private var translate = 0f

    var colors: IntArray = intArrayOf()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @Suppress("DEPRECATION")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView)
            startColor = a.getColor(
                R.styleable.GradientTextView_gradient_startColor,
                resources.getColor(R.color.color_03DAC5)
            )
            endColor = a.getColor(
                R.styleable.GradientTextView_gradient_endColor,
                resources.getColor(R.color.color_6200EE)
            )
            translateAnimate =
                a.getBoolean(R.styleable.GradientTextView_gradient_animate, false)
            translateSpeed = a.getInt(R.styleable.GradientTextView_gradient_speed, normal)

            direction = a.getInt(R.styleable.GradientTextView_gradient_direction, leftToRight)

            initColors()

            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        paint.shader = mLinearGradient
        super.onDraw(canvas)

        if (translateAnimate) {
            if (direction == leftToRight) {
                translate += measuredWidth / translateSpeed
                if (translate > measuredWidth) {
                    translate = 0f
                }
                mGradientMatrix.setTranslate(translate, 0f)
            } else {
                translate += measuredHeight / translateSpeed
                if (translate > measuredHeight) {
                    translate = 0f
                }
                mGradientMatrix.setTranslate(0f, translate)
            }
            mLinearGradient?.setLocalMatrix(mGradientMatrix)
            postInvalidateDelayed(100)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setupGradient()
    }

    private fun setupGradient() {
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            return
        }
        when (direction) {
            leftToRight ->
                if (translateAnimate) {
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        measuredWidth.toFloat(),
                        0f,
                        colors,
                        null,
                        Shader.TileMode.CLAMP
                    )
                } else {
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        measuredWidth.toFloat(),
                        0f,
                        startColor,
                        endColor,
                        Shader.TileMode.CLAMP
                    )
                }
            topToBottom ->
                if (translateAnimate) {
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        0f,
                        measuredHeight.toFloat(),
                        colors,
                        null,
                        Shader.TileMode.CLAMP
                    )
                } else {
                    mLinearGradient = LinearGradient(
                        0f,
                        0f,
                        0f,
                        measuredHeight.toFloat(),
                        startColor,
                        endColor,
                        Shader.TileMode.CLAMP
                    )
                }
        }
    }

    fun setColor(@ColorInt startColor: Int, @ColorInt endColor: Int) {
        this.startColor = startColor
        this.endColor = endColor
        initColors()
    }

    fun initColors() {
        if (translateAnimate) {
            colors = intArrayOf(startColor, endColor, startColor, startColor, startColor)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        translateAnimate = false
    }

    companion object {
        /**
         * direction: from left to right
         */
        const val leftToRight = 1

        /**
         * direction: from top to bottom
         */
        const val topToBottom = 2

        /**
         * translate speed slow
         */
        const val slow = 20

        /**
         * translate speed normal
         */
        const val normal = 10

        /**
         * translate speed fast
         */
        const val fast = 5
    }

}
