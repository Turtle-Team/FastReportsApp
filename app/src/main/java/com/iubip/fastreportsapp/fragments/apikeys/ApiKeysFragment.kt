package com.iubip.fastreportsapp.fragments.apikeys

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.adapters.apikeys.ApiKeyAdapter
import com.iubip.fastreportsapp.databinding.FragmentApiKeysBinding
import com.iubip.fastreportsapp.databinding.FragmentExportsBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.exports.ExportsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiKeysFragment : Fragment() {

    private val viewModel by viewModels<ApiKeysViewModel>()
    private lateinit var binding: FragmentApiKeysBinding
    private var apiKeyAdapter = ApiKeyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiKeysBinding.inflate(inflater, container, false)

        viewModel.getApiKeys()

        observableData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apiKeysRcView.adapter = apiKeyAdapter
        observableData()
    }

    private fun observableData() {
        viewModel.apikeys.observe(viewLifecycleOwner) {
            Log.e("API", it.toString())
            apiKeyAdapter.submitList(it)
        }
    }

//    private fun observableData(){
//        viewModel.apikeys.observe(viewLifecycleOwner){
////            binding.textApiKeys.text = it.toString()
//        }
//    }
}