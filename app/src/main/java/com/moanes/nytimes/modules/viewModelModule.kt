package com.moanes.nytimes.modules

import com.moanes.nytimes.ui.articledetail.ArticleDetailViewModel
import com.moanes.nytimes.ui.articleslist.ArticleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleListViewModel(get()) }
    viewModel { ArticleDetailViewModel() }
}