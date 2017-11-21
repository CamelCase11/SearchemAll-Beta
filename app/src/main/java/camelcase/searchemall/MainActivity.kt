package camelcase.searchemall

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.container_layout.*
import java.io.File

// TODO file url handling
// TODO fullscreen video support
// TODO Proper UI implementation

class MainActivity : AppCompatActivity() {

    private val scopeArray = arrayOf("web", "images", "videos", "torrents", "books")
    private var selectedScope: String? = null
    private var viewpagerFragment: FragmentViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nestedScrollView.isFillViewport = true

        initSpinner()
        val fileDownloadTask = FileDownloadTask(this)
        fileDownloadTask.start() // downloads a file if does not exists.
        val file = initFile()
        if (file.exists()) {
            val fileToString = getFileString(file)
            val myJsonHandler = MyJsonHandler(fileToString)
            perform_search.setOnClickListener(bindOnClickListener(myJsonHandler))
        } else {
            Toast.makeText(
                    this,
                    "Can not download url file, check your network",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    // populate spinner with entries
    private fun initSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.search_scopes, R.layout.spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        search_scope.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedScope = scopeArray[0]
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedScope = scopeArray[p2]
            }
        }
        search_scope.adapter = arrayAdapter
    }

    // add viewpager fragment to container
    private fun replaceFragment(arrayList: ArrayList<SearchEngineProperties>?) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        viewpagerFragment = FragmentViewPager()
        viewpagerFragment?.pageData = arrayList
        ft.replace(R.id.container,viewpagerFragment)
        ft.commit()
    }

    // get file as a string. Useful for files with text inside.
    private fun getFileString(file: File): String = file.inputStream().bufferedReader().use { it.readText() }

    // Initialize file in internal storage/Android/data/package/cache directory
    private fun initFile(): File {
        val filename = getString(R.string.search_scopes_file_name)
        return File(externalCacheDir, filename)
    }

    // onclick listener for search button
    private fun bindOnClickListener(jsonHandler: MyJsonHandler?): View.OnClickListener {
        return View.OnClickListener {
            /**
             * check for valid text entry and perform search
             */
            val typedQuery = search_query.text.toString().trim()
            if (typedQuery == "")
                Toast.makeText(baseContext, "Please Enter a valid Query", Toast.LENGTH_SHORT).show()
            else {
                appbar.setExpanded(false)
                val searchArray = jsonHandler?.getSearchList(selectedScope, typedQuery)
                replaceFragment(searchArray)
            }
        }
    }

    // not working
    override fun onBackPressed() {
        val pagerAdapter = viewpagerFragment?.getPagerAdapter()
        val webViewFrag = pagerAdapter?.getItem(pagerAdapter.currentItemNumber) as FragmentWebview
        val webView = webViewFrag.getWebView()
        if (webView != null && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
