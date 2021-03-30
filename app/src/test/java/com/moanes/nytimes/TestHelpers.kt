package com.moanes.nytimes

import com.moanes.nytimes.data.models.Article

fun getArticle(): List<Article> {
    val list = ArrayList<Article>()
    for (i in 1..10) {
        val article = Article(id = i.toLong())
        list.add(article)
    }
    return list
}
fun getArticleEmpty(): List<Article> {
    val list = ArrayList<Article>()
    return list
}