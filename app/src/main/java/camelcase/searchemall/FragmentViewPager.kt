package camelcase.searchemall

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_viewpager.view.*

class FragmentViewPager : Fragment() {

    var pageData: ArrayList<SearchEngineProperties> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, container, false)
        view.viewpager.adapter = PagerAdapter(fragmentManager, pageData)
        view.tabLayout.setupWithViewPager(view.viewpager)
        return view
    }
}