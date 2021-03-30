package com.moanes.nytimes.ui.articleslist

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.moanes.nytimes.R
import com.moanes.nytimes.base.BaseActivity
import com.moanes.nytimes.data.models.Article
import com.moanes.nytimes.ui.articledetail.ArticleDetailActivity
import com.moanes.nytimes.ui.articledetail.ArticleDetailFragment
import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleListActivity : BaseActivity() {
    private val viewModel: ArticleListViewModel by viewModel()
    private lateinit var adapter: ArticlesListAdapter

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun getLayout(): Int {
        return R.layout.activity_article_list
    }

    override fun init() {
        handleError(viewModel)
        handleProgress(viewModel)

        initToolbar()
        checkDeviceType()

        initArticlesListRV()
        handleArticlesListLiveData()

    }


    private fun checkDeviceType() {
        if (findViewById<NestedScrollView>(R.id.article_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun handleArticlesListLiveData() {
        viewModel.getArticleList().observe(this, {
            if (null != it && this::adapter.isInitialized)
                adapter.submitList(it.toMutableList())
        })
    }

    private fun initArticlesListRV() {
        adapter = ArticlesListAdapter(::openArticle)
        article_list.layoutManager = LinearLayoutManager(this)
        article_list.adapter = adapter
    }

    private fun openArticle(article: Article) {
        if (twoPane) {
            val fragment = ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ArticleDetailFragment.ARG_ITEM, article)
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.article_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, ArticleDetailActivity::class.java).apply {
                putExtra(ArticleDetailFragment.ARG_ITEM, article)
            }
            startActivity(intent)
        }
    }
}