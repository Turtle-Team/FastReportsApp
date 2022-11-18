package com.iubip.fastreportsapp.fragments.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentTemplateBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemplateFragment : Fragment() {

    private val viewModel by viewModels<TemplateViewModel>()
    private lateinit var binding: FragmentTemplateBinding
    private var templateAdapter = BaseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTemplateBinding.inflate(inflater, container, false)

        viewModel.getContentFolder()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.templatesRcView.adapter = templateAdapter
        observableData()
    }

    private fun observableData(){
        viewModel.response.observe(viewLifecycleOwner){
            templateAdapter.submitList(it)
        }
    }
}