package com.future.awaker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruzhan.awaker.article.ArticleHomeFragment
import com.ruzhan.awaker.article.comment.ArticleHotCommentFragment
import com.ruzhan.awaker.article.news.ArticleMovieListFragment
import kotlinx.android.synthetic.main.frag_main.*

/**
 * Created by ruzhan123 on 2018/8/28.
 */
class MainFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private val fragmentMap = HashMap<String, Fragment>()

    private var articleHomeFragment: ArticleHomeFragment? = null
    private var articleHotCommentFragment: ArticleHotCommentFragment? = null
    private var articleMovieListFragment: ArticleMovieListFragment? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation.setTextVisibility(false)
        bottom_navigation.enableAnimation(false)

        bottom_navigation.setOnNavigationItemSelectedListener {
            if (bottom_navigation.selectedItemId != it.itemId) {
                replaceFragment(it.itemId)
            }
            true
        }

        replaceFragment(R.id.article)
    }

    private fun replaceFragment(tabId: Int) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()

        for (frag in fragmentMap.values) {
            transaction.hide(frag)
        }

        var fragTag: String? = null
        var frag: Fragment? = null

        when (tabId) {
            R.id.article -> {
                fragTag = "ArticleHomeFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArticleHomeFragment.newInstance()
                    articleHomeFragment = frag
                    transaction.add(R.id.container, frag, fragTag)

                } else {
                    transaction.show(frag)
                }

            }
            R.id.comment -> {
                fragTag = "ArticleHotCommentFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArticleHotCommentFragment.newInstance()
                    articleHotCommentFragment = frag
                    transaction.add(R.id.container, frag, fragTag)

                } else {
                    transaction.show(frag)
                }
            }
            R.id.movie -> {
                fragTag = "ArticleMovieListFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArticleMovieListFragment.newInstance()
                    articleMovieListFragment = frag
                    transaction.add(R.id.container, frag, fragTag)

                } else {
                    transaction.show(frag)
                }
            }
        }
        if (fragTag != null && frag != null) {
            fragmentMap[fragTag] = frag
        }

        if (!fm.isDestroyed) {
            transaction.commitAllowingStateLoss()
        }
    }
}