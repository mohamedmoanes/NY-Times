package com.moanes.nytimes.ui.articledetail

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.View
import com.moanes.nytimes.R
import com.moanes.nytimes.base.BaseFragment
import com.moanes.nytimes.data.models.Article
import kotlinx.android.synthetic.main.article_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a [ArticleListActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : BaseFragment() {
    private val viewModel: ArticleDetailViewModel by viewModel()

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Article? = null


    override fun getLayout(): Int {
        return R.layout.article_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getParcelable<Article>(ARG_ITEM)
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    item?.title
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        handleLoadWepPageLiveData()
    }

    private fun handleLoadWepPageLiveData() {
        item?.let { article ->
            article.url?.let { url ->
                viewModel.loadWebPage(url).observe(viewLifecycleOwner, {
                    article_detail.text = it
                })
            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM = "URL"
    }
}