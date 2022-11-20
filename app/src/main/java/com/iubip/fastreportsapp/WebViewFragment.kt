package com.iubip.fastreportsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iubip.fastreportsapp.databinding.FragmentAdditionallyBinding
import com.iubip.fastreportsapp.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        binding.webView.loadUrl("https://fastreport.cloud/download/e/6379bf215f620ebfce9a4dac?preview=true", )

        return binding.root
    }
}