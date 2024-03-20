package com.tinkoff.education.emoji

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding

class EmojiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            changeMessage()
        }
    }

    private fun changeMessage() {
        val messageView = findViewById<MessageView>(R.id.messageView).apply {
            setBackgroundResource(R.drawable.emoji_unselected_bg)
            setPadding(48)
            avatarImageView.setImageResource(R.drawable.me_ic)

            nameTextView.text = "Я"
            nameTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
            nameTextView.setTypeface(null, Typeface.BOLD)
            nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)


            messageTextView.text =
                "Кастом вью не вызывают никаких сложностей. Появившиеся у меня после них " +
                        "седые волосы лишь говорят о приобретенной мудрости. " +
                        "Ничего страшного в том, что я убил на это все воскресенье"
            messageTextView.setTextColor(ContextCompat.getColor(context, R.color.white))


            reactionsLayout.addPlusButton().apply {
                setPadding(8)
                setColorFilter(getColor(R.color.white))
                background = AppCompatResources.getDrawable(context, R.drawable.emoji_unselected_bg)
            }

            reactionsLayout.setPlusButtonClickListener {
                val newEmojiView = EmojiView(this@EmojiActivity)
                newEmojiView.emoji = emojis.random()
                newEmojiView.setPadding(16)
                newEmojiView.background =
                    AppCompatResources.getDrawable(this@EmojiActivity, R.drawable.emoji_bg)

                val layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(4, 4, 4, 4)
                newEmojiView.layoutParams = layoutParams

                newEmojiView.setOnClickListener { emojiView ->
                    (emojiView as EmojiView).count++
                    emojiView.isSelected = !emojiView.isSelected

                }
                reactionsLayout.addViewWithLayout(newEmojiView)
            }
        }
    }

    companion object {
        val emojis = listOf(
            "\uD83D\uDE00", // 😀
            "\uD83D\uDE01", // 😁
            "\uD83D\uDE02", // 😂
            "\uD83D\uDE03", // 😃
            "\uD83D\uDE04", // 😄
            "\uD83D\uDE05", // 😅
            "\uD83D\uDE06", // 😆
            "\uD83D\uDE07", // 😇
            "\uD83D\uDE08", // 😈
            "\uD83D\uDE09", // 😉
            "\uD83D\uDE0A", // 😊
            "\uD83D\uDE0B", // 😋
            "\uD83D\uDE0C", // 😌
            "\uD83D\uDE0D", // 😍
            "\uD83D\uDE0E", // 😎
        )
    }
}