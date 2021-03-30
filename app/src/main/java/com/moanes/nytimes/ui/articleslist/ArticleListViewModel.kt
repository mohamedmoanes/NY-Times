package com.moanes.nytimes.ui.articleslist

import com.moanes.nytimes.base.BaseViewModel
import com.moanes.nytimes.data.models.Article
import com.moanes.nytimes.data.repositories.ArticlesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleListViewModel(private val articlesRepo: ArticlesRepo) : BaseViewModel() {

    fun getArticleList() = handelRequestLiveData<List<Article>> {
        val result = withContext(Dispatchers.IO) {
            articlesRepo.getMostPopularArticles()
        }
        showNoData.value = result.isNullOrEmpty()
        emit(result)
    }

}