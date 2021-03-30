package com.moanes.nytimes.modules

import com.moanes.nytimes.data.repositories.ArticlesRepo
import com.moanes.nytimes.data.repositories.ArticlesRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory<ArticlesRepo> { ArticlesRepoImpl(get()) }
}