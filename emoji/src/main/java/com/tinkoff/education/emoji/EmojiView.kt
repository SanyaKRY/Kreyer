package com.tinkoff.education.emoji

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.withStyledAttributes

class EmojiView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
    defTheme: Int = 0
) : View(context, attributeSet, defStyle, defTheme) {

    var emoji: String = "\uD83D\uDD25"
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    var count: Int = 0
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    private var marginLeft = 0
    private var marginTop = 0
    private var marginRight = 0
    private var marginBottom = 0

    init {
        context.withStyledAttributes(attributeSet, R.styleable.EmojiView) {
            count = getInt(R.styleable.EmojiView_count, 0)
        }
    }

    private val textToDraw: String
        get() {
            return if (count != 0) "$emoji $count"
            else emoji
        }

    private val textPaint = TextPaint().apply {
        color = getContext().getColor(R.color.white)
        textSize = 16f.sp(context)
    }

    private val textRect = Rect()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        textPaint.getTextBounds(textToDraw, 0, textToDraw.length, textRect)

        val actualWidth = resolveSize(paddingRight + paddingLeft + textRect.width() + marginLeft + marginRight, widthMeasureSpec)
        val actualHeight = resolveSize(paddingTop + paddingBottom + textRect.height() + marginTop + marginBottom, heightMeasureSpec)

        setMeasuredDimension(
            actualWidth,
            actualHeight
        )
    }

    override fun onDraw(canvas: Canvas) {
        val topOffset = height / 2 - textRect.exactCenterY()
        canvas.drawText(textToDraw, paddingLeft.toFloat() + marginLeft, topOffset + marginTop, textPaint)
    }

    private fun Float.sp(context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics
    )
}