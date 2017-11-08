package camelcase.searchemall

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.container_layout.*
import java.io.File

// TODO add code to store that in cache and parse it
// TODO file url handling
// TODO fullscreen video support
// TODO Proper UI implementation

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nestedScrollView.isFillViewport = true

        initSpinner()
        val fileDownloadTask = FileDownloadTask(this)
        val filename = getString(R.string.search_scopes_file_name)

        perform_search.setOnClickListener {
            appbar.setExpanded(false)
            fileDownloadTask.start()
            val file = File(externalCacheDir, filename)
            val fileToString = file.inputStream().bufferedReader().use { it.readText() }
//            print(fileToString)
            val myJsonHandler = MyJsonHandler(fileToString)
            val searchArray = myJsonHandler.getSearchList("web")
            for (i in searchArray) {
                print("name : ${i.name}, url : ${i.url}, enableJs : ${i.enableJs}")
            }
            //            replaceFragment()
        }
    }

    // populate spinner with entries
    private fun initSpinner() {
        val arrayAdapter:ArrayAdapter<CharSequence> =
                ArrayAdapter.createFromResource(this,R.array.search_scopes,R.layout.spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        search_scope.adapter = arrayAdapter
    }

    // add viewpager fragment to container
    private fun replaceFragment() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val viewpagerFragment = FragmentViewPager()
        ft.replace(R.id.container,viewpagerFragment)
        ft.commit()
    }
}
