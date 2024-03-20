package com.tinkoff.education.emoji

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class FlexboxLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
    defTheme: Int = 0
) : ViewGroup(context, attributeSet, defStyle, defTheme) {

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var currentLeft = paddingLeft
        var currentTop = paddingTop
        var maxHeightInLine = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val layoutParams = child.layoutParams as MarginLayoutParams

            if (currentLeft + child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin > width - paddingRight) {
                currentLeft = paddingLeft
                currentTop += maxHeightInLine
                maxHeightInLine = 0
            }

            val left = currentLeft + layoutParams.leftMargin
            val top = currentTop + layoutParams.topMargin
            val right = left + child.measuredWidth
            val bottom = top + child.measuredHeight
            child.layout(left, top, right, bottom)

            currentLeft += child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
            maxHeightInLine =
                maxHeightInLine.coerceAtLeast(child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin)
        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        var width = 0
        var height = 0
        var curLineWidth = 0
        var maxHeightInLine = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val layoutParams = child.layoutParams as MarginLayoutParams
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, height)

            if (curLineWidth + child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin <= widthSize - paddingLeft - paddingRight) {
                curLineWidth += child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
                maxHeightInLine =
                    maxHeightInLine.coerceAtLeast(child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin)
            } else {
                width = width.coerceAtLeast(curLineWidth)
                height += maxHeightInLine
                curLineWidth = child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
                maxHeightInLine = child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin
            }
        }

        width = width.coerceAtLeast(curLineWidth)
        height += maxHeightInLine

        width += paddingLeft + paddingRight
        height += paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSizeAndState(width, widthMeasureSpec, 0),
            resolveSizeAndState(height, heightMeasureSpec, 0)
        )
    }

    private var plusButtonClickListener: OnClickListener? = null

    private val plusButton by lazy {
        val button = ImageView(context)
        button.setImageResource(R.drawable.add_ic)
        button.setOnClickListener {
            plusButtonClickListener?.onClick(it)
        }
        super.addView(button)
        button
    }

    fun setPlusButtonClickListener(listener: OnClickListener?) {
        plusButtonClickListener = listener
    }

    fun addPlusButton(): ImageView {
        return plusButton
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun checkLayoutParams(layoutParams: LayoutParams?): Boolean {
        return layoutParams is MarginLayoutParams
    }

    fun addViewWithLayout(view: View) {
        addView(view, childCount - 1)
        requestLayout()
    }
}