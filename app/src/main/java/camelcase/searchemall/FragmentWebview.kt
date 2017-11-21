package camelcase.searchemall

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_webview.*
import kotlinx.android.synthetic.main.fragment_webview.view.*

class FragmentWebview : Fragment() {
    var url: String? = null
    var enableJs: Boolean? = true
    private var currentView: View? = null
    private var framelayout: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        view.webview.webViewClient = WebViewClient()
        view.webview.settings.javaScriptEnabled = (enableJs == true)

        // get pageloading progress from webchromeclient
        view.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webview: WebView?, newProgress: Int) {
                view.progressbar.progress = newProgress
                if (newProgress == 100) view.progressbar.visibility = View.GONE
            }

            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                framelayout = view as FrameLayout
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                framelayout = null
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        // if any error occur while loading page show toast message
        view.webview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Toast.makeText(context, "Error Loading Page", Toast.LENGTH_SHORT).show()
            }
        }

        view.webview.loadUrl(url)
        currentView = view
        return view
    }

    override fun onPause() {
        super.onPause()
        webview.onPause()
    }

    fun getWebView(): WebView? = currentView?.webview
}

