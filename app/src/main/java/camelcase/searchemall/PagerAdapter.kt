package camelcase.searchemall

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager?, pageArray: ArrayList<SearchEngineProperties>?) : FragmentStatePagerAdapter(fm) {

    private val pageData = pageArray
    var currentItemNumber = 0

    override fun getItem(position: Int): Fragment {
        val fragment = FragmentWebview()
        fragment.url = pageData?.get(position)?.url
        fragment.enableJs = pageData?.get(position)?.enableJs
        currentItemNumber = position
        return fragment
    }

    override fun getCount(): Int = pageData?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? = pageData?.get(position)?.name
}