package com.naulian.article.models

import com.google.firebase.firestore.Exclude

data class Article(
    @get:Exclude
    val chapterId: String = "",
    val index : Int = 0,

    //info
    val title: String = "",
    val blocks: List<Block> = emptyList()
)
