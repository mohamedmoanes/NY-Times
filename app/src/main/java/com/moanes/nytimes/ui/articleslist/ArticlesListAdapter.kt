package com.moanes.nytimes.ui.articleslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.moanes.nytimes.R
import com.moanes.nytimes.data.models.Article
import com.moanes.nytimes.utils.extensions.setImageURL

class ArticlesListAdapter(private val open: (article: Article) -> Unit) :
    ListAdapter<Article, ArticlesListAdapter.ViewHolder>(ArticleItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_list_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)

        if (!article.media.isNullOrEmpty())
            article.media.first {
                if (!it.mediaMetadata.isNullOrEmpty())
                    it.mediaMetadata.first { image ->
                        if (!image.url.isNullOrBlank())
                            holder.image.setImageURL(image.url)
                        true
                    }
                true
            }
        holder.title.text = article.title
        holder.abstractTV.text = article.abstract
        holder.writer.text = article.byline
        holder.date.text = article.publishedDate

        holder.itemView.setOnClickListener {
            open(article)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.image)
        val title: MaterialTextView = view.findViewById(R.id.title)
        val abstractTV: MaterialTextView = view.findViewById(R.id.abstractTV)
        val writer: MaterialTextView = view.findViewById(R.id.writer)
        val date: MaterialTextView = view.findViewById(R.id.date)
    }

    class ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}