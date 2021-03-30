package com.moanes.nytimes.ui.articledetail

import android.text.Spannable
import com.moanes.nytimes.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.nightwhistler.htmlspanner.HtmlSpanner
import java.net.URL

class ArticleDetailViewModel : BaseViewModel() {
    fun loadWebPage(url: String) = handelRequestLiveData<Spannable> {
        val result = withContext(Dispatchers.IO) {
            URL(url).readText()
        }

        val articleHtml = withContext(Dispatchers.Default) {
            val start = result.indexOf("<article")
            val end = result.indexOf("</article>")

            result.subSequence(start, end)
        }
        val html = withContext(Dispatchers.IO) {
            HtmlSpanner().fromHtml(articleHtml.toString())
        }

        emit(html)
    }

}