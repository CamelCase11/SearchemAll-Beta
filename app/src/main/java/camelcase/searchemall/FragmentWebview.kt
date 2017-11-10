package camelcase.searchemall

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_webview.*
import kotlinx.android.synthetic.main.fragment_webview.view.*

class FragmentWebview : Fragment() {
    var url: String? = null
    var enableJs: Boolean? = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        view.webview.webViewClient = WebViewClient()
        view.webview.settings.javaScriptEnabled = (enableJs == true)

        // get pageloading progress from webchromeclient
        view.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webview: WebView?, newProgress: Int) {
                view.progressbar.setProgress(newProgress)
                if (newProgress == 100) view.progressbar.visibility = View.GONE
            }
        }

        // if any error occur while loading page show toast message
        view.webview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Toast.makeText(context, "Error Loading Page", Toast.LENGTH_SHORT).show()
            }
        }

        view.webview.loadUrl(url)

        return view
    }

    override fun onPause() {
        super.onPause()
        webview.onPause()
    }
}

