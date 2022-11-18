package com.iubip.fastreportsapp.fragments.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.databinding.FragmentDocumentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocumentsFragment : Fragment() {

    private val viewModel by viewModels<DocumentsViewModel>()
    private lateinit var binding: FragmentDocumentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false)

        viewModel.getContentFolder()
        observableData()

        return binding.root
    }

    fun observableData(){
        viewModel.response.observe(viewLifecycleOwner){
            binding.baseText.text = it.toString()
        }
    }
}