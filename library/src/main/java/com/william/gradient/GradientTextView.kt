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
    private var mStartColor = 0
    private var mEndColor = 0
    private var mDirection = 0
    private var mTranslate = 0f
    private var mTranslateSpeed = 0
    var translateAnimate = false

    @ColorInt
    lateinit var colors: IntArray

    /**
     * direction: from left to right
     */
    private val leftToRight = 1

    /**
     * direction: from top to bottom
     */
    private val topToBottom = 2

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
            mStartColor = a.getColor(
                R.styleable.GradientTextView_startColor,
                resources.getColor(R.color.color_03DAC5)
            )
            mEndColor = a.getColor(
                R.styleable.GradientTextView_endColor,
                resources.getColor(R.color.color_6200EE)
            )
            translateAnimate =
                a.getBoolean(R.styleable.GradientTextView_translateAnimate, false)
            mTranslateSpeed = a.getInt(R.styleable.GradientTextView_translateSpeed, 10)

            mDirection = a.getInt(R.styleable.GradientTextView_direction, leftToRight)

            if (translateAnimate) {
                colors = intArrayOf(mStartColor, mEndColor, mStartColor, mStartColor, mStartColor)
            }
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        paint.shader = mLinearGradient
        super.onDraw(canvas)

        if (translateAnimate) {
            if (mDirection == leftToRight) {
                mTranslate += measuredWidth / mTranslateSpeed
                if (mTranslate > measuredWidth) {
                    mTranslate = 0f
                }
                mGradientMatrix.setTranslate(mTranslate, 0f)
            } else {
                mTranslate += measuredHeight / mTranslateSpeed
                if (mTranslate > measuredHeight) {
                    mTranslate = 0f
                }
                mGradientMatrix.setTranslate(0f, mTranslate)
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
        when (mDirection) {
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
                        mStartColor,
                        mEndColor,
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
                        mStartColor,
                        mEndColor,
                        Shader.TileMode.CLAMP
                    )
                }
        }
    }

}
