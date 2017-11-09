package camelcase.searchemall

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager?, pageArray: ArrayList<SearchEngineProperties>) : FragmentStatePagerAdapter(fm) {

    private val pageData = pageArray

    override fun getItem(position: Int): Fragment {
        val fragment = FragmentTest()
        fragment.url = pageData[position].url
        return fragment
    }

    override fun getCount(): Int {
        return pageData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageData[position].name
    }
}