package camelcase.searchemall

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm:FragmentManager?) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return FragmentTest()
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "TAB"
    }
}