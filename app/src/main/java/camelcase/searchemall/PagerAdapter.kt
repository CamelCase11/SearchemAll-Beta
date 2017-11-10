package camelcase.searchemall

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager?, pageArray: ArrayList<SearchEngineProperties>?) : FragmentStatePagerAdapter(fm) {

    private val pageData = pageArray

    override fun getItem(position: Int): Fragment {
        val fragment = FragmentWebview()
        fragment.url = pageData?.get(position)?.url
        fragment.enableJs = pageData?.get(position)?.enableJs
        return fragment
    }

    override fun getCount(): Int {
        if (pageData != null) {
            return pageData.size
        } else {
            return 0
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageData?.get(position)?.name
    }
}