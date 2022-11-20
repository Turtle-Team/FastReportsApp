package com.iubip.fastreportsapp.fragments.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentTemplateBinding
import com.iubip.fastreportsapp.databinding.FragmentUsersBinding
import com.iubip.fastreportsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

//        val basic: HashMap<String, String> = HashMap<String, String>()
//        val host: HashMap<String, String> = HashMap<String, String>()
//        basic.put("Authorization", Constants.BASIC_AUTH)
//        host.put("Host", "fastreport.cloud")

//        val headers = hashMapOf<>()

//        binding.webView.loadUrl("https://stackoverflow.com/questions/39939121/android-webview-add-header-to-http-request", )

//        val headers: HashMap<String, String> = HashMap<String, String>()
//
//        headers.put("Authorization", Constants.BASIC_AUTH)
//        headers.put("Host", "fastreport.cloud")
//        headers.put("Content-Type", "text/xml")
//
//        binding.webView.settings.javaScriptEnabled = true
//        binding.webView.loadUrl("https://fastreport.cloud/download/t/63792f985f620ebfce9a32a1",headers)
//
//
//        val headers: HashMap<String, String> = HashMap<String, String>()
//        headers["Authorization"] = Constants.BASIC_AUTH
//        headers["Host"] = "fastreport.cloud"
//
//
//        binding.webView.webChromeClient = WebChromeClient()
//        binding.webView.settings.javaScriptEnabled = true
//
//        binding.webView.webViewClient = object : WebViewClient(){
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): Boolean {
//                view?.loadUrl("https://fastreport.cloud/download/t/6379589b5f620ebfce9a4358", headers)
//                return true
//            }
//        }
//        binding.webView.loadUrl("https://fastreport.cloud/download/t/6379589b5f620ebfce9a4358", headers)



        binding.webView.webViewClient = WebViewClient()

        return binding.root
    }
}