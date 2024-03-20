package com.tinkoff.education.emoji

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    var avatarImageView: ImageView
    var nameTextView: TextView
    var messageTextView: TextView
    var reactionsLayout: FlexboxLayout

    init {
        avatarImageView = ImageView(context).apply {
            setImageResource(R.mipmap.ic_launcher)
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            addView(this)
        }

        nameTextView = TextView(context).apply {
            text = "Username"
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            addView(this)
        }

        messageTextView = TextView(context).apply {
            text = "Message text"
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            addView(this)
        }

        reactionsLayout = FlexboxLayout(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            this@MessageView.addView(this)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        measureChildWithMargins(avatarImageView, widthMeasureSpec, 0, heightMeasureSpec, 0)
        measureChildWithMargins(nameTextView, widthMeasureSpec, 0, heightMeasureSpec, 0)
        measureChildWithMargins(messageTextView, widthMeasureSpec, 0, heightMeasureSpec, 0)
        measureChildWithMargins(reactionsLayout, widthMeasureSpec, 0, heightMeasureSpec, 0)

        val availableWidth =
            widthSize - paddingLeft - paddingRight - avatarImageView.measuredWidth - space
        val availableHeight = heightSize - paddingTop - paddingBottom

        nameTextView.measure(
            MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(availableHeight, MeasureSpec.AT_MOST)
        )
        messageTextView.measure(
            MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(availableHeight, MeasureSpec.AT_MOST)
        )

        val totalHeight = paddingTop + paddingBottom + 2 * space +
                maxOf(
                    avatarImageView.measuredHeight,
                    nameTextView.measuredHeight + messageTextView.measuredHeight
                ) +
                reactionsLayout.measuredHeight

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> {
                (paddingLeft + paddingRight + space +
                        avatarImageView.measuredWidth + nameTextView.measuredWidth + messageTextView.measuredWidth + reactionsLayout.measuredWidth).coerceAtMost(
                    widthSize
                )
            }

            else -> {
                paddingLeft + paddingRight + space +
                        maxOf(
                            avatarImageView.measuredWidth,
                            nameTextView.measuredWidth,
                            messageTextView.measuredWidth,
                            reactionsLayout.measuredWidth
                        )
            }
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> totalHeight.coerceAtMost(heightSize)
            else -> totalHeight
        }

        setMeasuredDimension(
            resolveSizeAndState(width, widthMeasureSpec, 0),
            resolveSizeAndState(height, heightMeasureSpec, 0)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val avatarTop = paddingTop
        val avatarLeft = paddingLeft
        avatarImageView.layout(
            avatarLeft,
            avatarTop,
            avatarLeft + avatarImageView.measuredWidth,
            avatarTop + avatarImageView.measuredHeight
        )

        val nameTop = paddingTop
        val nameLeft = avatarLeft + avatarImageView.measuredWidth + space
        nameTextView.layout(
            nameLeft,
            nameTop,
            nameLeft + nameTextView.measuredWidth,
            nameTop + nameTextView.measuredHeight
        )

        val messageTop = nameTop + nameTextView.measuredHeight + space
        messageTextView.layout(
            nameLeft,
            messageTop,
            nameLeft + messageTextView.measuredWidth,
            messageTop + messageTextView.measuredHeight
        )

        val reactionsTop = maxOf(
            avatarTop + avatarImageView.measuredHeight,
            messageTop + messageTextView.measuredHeight
        ) + space
        reactionsLayout.layout(
            paddingLeft,
            reactionsTop,
            paddingLeft + reactionsLayout.measuredWidth,
            reactionsTop + reactionsLayout.measuredHeight
        )
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

    override fun generateLayoutParams(layoutParams: LayoutParams?): LayoutParams {
        return MarginLayoutParams(layoutParams)
    }

    companion object {
        const val space = 32
    }
}