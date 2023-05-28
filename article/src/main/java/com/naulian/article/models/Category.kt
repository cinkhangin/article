package com.naulian.article.models

import androidx.annotation.DrawableRes
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import com.naulian.article.R

data class Category(
    @get:Exclude
    val courseId: String = "",
    val index : Int = 0,

    @DrawableRes
    val image: Int = R.drawable.placeholder,

    val title: String = "",
    val body: String = "",
    val author: String = "",

    val tags: List<Tag> = emptyList(),
    val articles: List<Article> = emptyList(),

    //status
    val publish: Boolean = false,

    //meta-data
    @ServerTimestamp
    val date: Timestamp? = null,
)
