package com.moanes.nytimes.ui.articleslist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.moanes.nytimes.R
import com.moanes.nytimes.utils.FakeArticlesList
import junit.framework.TestCase
import kotlinx.android.synthetic.main.article_list.*
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleListActivityTest : TestCase() {
    @get:Rule
    var activityRule =
        ActivityScenarioRule(ArticleListActivity::class.java)

    @Before
    fun setup() {
        activityRule.scenario.onActivity {
            val adapter = ArticlesListAdapter {}
            it.article_list.adapter = adapter
            it.article_list.layoutManager = LinearLayoutManager(it)
            adapter.submitList(FakeArticlesList.articlesList)
        }
    }

    @Test
    fun test_isListVisible_onAppLaunch() {
        activityRule.scenario.onActivity {
            onView(withId(it.article_list.id)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testItemClick() {
        activityRule.scenario.onActivity {
            onView(withId(it.article_list.id))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )
        }
    }
}