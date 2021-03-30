package com.moanes.nytimes.ui.articleslist

import com.moanes.nytimes.base.BaseViewModel
import com.moanes.nytimes.data.repositories.ArticlesRepo

class ArticleListViewModel(private val articlesRepo: ArticlesRepo): BaseViewModel() {

    fun getArticleList()=callRequestLiveData { articlesRepo.getMostPopularArticles() }
}