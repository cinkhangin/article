package com.naulian.article.models

import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.naulian.anhance.toUri
import com.naulian.article.R

data class Block(
    val index: Int = 0,
    val groupId: String = "",

    val header: String = "",
    val title: String = "",
    val label: String = "",

    @DrawableRes
    val drawable: Int = 0,
    val image: String = "",
    val description: String = "",

    val quote: String = "",
    val author: String = "Undefined",

    val body: String = "",
    val note: String = "",

    val code: String = "",
    val language: String = "",

    @ColorRes
    val bgColor: Int = 0,
    val bgHex: String = "",

    val hint: String = "",
    val helper : String = "",
    val input : String = "",

    val button: String = "",
    val action: ((Block) -> Unit)? = null
) {
    val quoteText
        get() = """
        |$quote
        |-$author
    """.trimMargin()

    fun imageUri(context: Context): Uri {
        return if (image.isNotEmpty()) Uri.parse(image)
        else if (drawable != 0) drawable.toUri(context)
        else R.drawable.placeholder.toUri(context)
    }

    fun background(context: Context): Int {
        return if (bgHex.isNotEmpty()) Color.parseColor(bgHex)
        else if (bgColor != 0) context.getColor(bgColor)
        else context.getColor(R.color.background)
    }
}
