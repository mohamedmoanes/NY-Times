package com.moanes.nytimes.data.repositories

import com.moanes.nytimes.data.models.Article
import com.moanes.nytimes.data.network.Remote

interface ArticlesRepo {
    suspend fun getMostPopularArticles(): List<Article>
}

class ArticlesRepoImpl(private val remote: Remote) : ArticlesRepo {
    override suspend fun getMostPopularArticles(): List<Article> {
        return remote.getMostPopularArticles().results
    }
}