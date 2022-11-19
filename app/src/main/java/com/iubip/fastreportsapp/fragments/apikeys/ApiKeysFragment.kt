package com.iubip.fastreportsapp.fragments.apikeys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentApiKeysBinding
import com.iubip.fastreportsapp.databinding.FragmentExportsBinding
import com.iubip.fastreportsapp.fragments.exports.ExportsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiKeysFragment : Fragment() {

    private val viewModel by viewModels<ApiKeysViewModel>()
    private lateinit var binding: FragmentApiKeysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiKeysBinding.inflate(inflater, container, false)

        viewModel.getApiKeys()

        observableData()

        return binding.root
    }

    private fun observableData(){
        viewModel.apikeys.observe(viewLifecycleOwner){
//            binding.textApiKeys.text = it.toString()
        }
    }
}