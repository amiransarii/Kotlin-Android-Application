package org.app.kotlinandroidapplication

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        /*  val wedData: String =  "<html><body><h1>Hello, Javatpoint!</h1></body></html>"
       val mimeType: String = "text/html"
       val utfType: String = "UTF-8"
       webView.loadData(wedData,mimeType,utfType)*/

        /* val myWebUrl: String = "file:///android_asset/index.html"
        webView.loadUrl(myWebUrl)*/

        webView.webViewClient =MyWebViewClient(this)
        webView.loadUrl("https://www.rakutentrade.my/")
    }
    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }

}
