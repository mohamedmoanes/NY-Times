package com.moanes.nytimes.ui.aritcleslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moanes.nytimes.TestCoroutineRule
import com.moanes.nytimes.data.models.Article
import com.moanes.nytimes.data.repositories.ArticlesRepo
import com.moanes.nytimes.getArticle
import com.moanes.nytimes.getArticleEmpty
import com.moanes.nytimes.ui.articleslist.ArticleListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ArticleListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var articlesRepo: ArticlesRepo

    @MockK
    private lateinit var loadingObserver: Observer<Boolean>

    @MockK
    private lateinit var noDataObserver: Observer<Boolean>

    @MockK
    private lateinit var errorsObserver: Observer<String>

    @MockK
    private lateinit var articlesObserver: Observer<List<Article>>

    private lateinit var subject: ArticleListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = ArticleListViewModel(articlesRepo)
    }

    @Test
    fun `getMovies success`() {
        // given
        coEvery { articlesRepo.getMostPopularArticles() } returns getArticle()

        subject.getArticleList().observeForever(articlesObserver)
        subject.showLoading.observeForever(loadingObserver)
        subject.showNoData.observeForever(noDataObserver)

        //when
        subject.getArticleList()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { articlesRepo.getMostPopularArticles() }
        verify { noDataObserver.onChanged(false) }
        verify { articlesObserver.onChanged(getArticle()) }
        verify { loadingObserver.onChanged(false) }
    }

    @Test
    fun `getMovies Empty`() {
        // given
        coEvery { articlesRepo.getMostPopularArticles() } returns getArticleEmpty()

        subject.getArticleList().observeForever(articlesObserver)
        subject.showLoading.observeForever(loadingObserver)
        subject.showNoData.observeForever(noDataObserver)

        //when
        subject.getArticleList()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { articlesRepo.getMostPopularArticles() }
        verify { noDataObserver.onChanged(true) }
        verify { articlesObserver.onChanged(getArticleEmpty()) }
        verify { loadingObserver.onChanged(false) }
    }

    @Test
    fun `getMovies failure`() {
        // given
        coEvery { articlesRepo.getMostPopularArticles() } throws IOException()

        subject.getArticleList().observeForever(articlesObserver)
        subject.errorLiveData.observeForever(errorsObserver)
        subject.showLoading.observeForever(loadingObserver)

        //when
        subject.getArticleList()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { articlesRepo.getMostPopularArticles() }
        verify { loadingObserver.onChanged(false) }
        verify { errorsObserver.onChanged(IOException().localizedMessage) }

    }
}