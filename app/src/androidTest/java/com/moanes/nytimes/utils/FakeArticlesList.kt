package com.moanes.nytimes.utils

import com.moanes.nytimes.data.models.Article

object FakeArticlesList {
    val articlesList = ArrayList<Article>(
        listOf(
            Article(
                id = 1,
                title = "title 1",
                abstract = "abstract",
                byline = "writer 1",
                publishedDate = "2/2/2020"
            ),
            Article(
                id = 2,
                title = "title 2",
                abstract = "abstract",
                byline = "writer 2",
                publishedDate = "2/2/2020"
            ),
            Article(
                id = 3,
                title = "title 3",
                abstract = "abstract",
                byline = "writer 3",
                publishedDate = "2/2/2020"
            ),
            Article(
                id = 4,
                title = "title 4",
                abstract = "abstract",
                byline = "writer 4",
                publishedDate = "2/2/2020"
            )
        )
    )
}