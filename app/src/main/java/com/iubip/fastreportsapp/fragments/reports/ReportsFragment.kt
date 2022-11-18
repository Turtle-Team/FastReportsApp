package com.iubip.fastreportsapp.fragments.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import com.iubip.fastreportsapp.databinding.FragmentTemplateBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.templates.TemplateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportsFragment : Fragment() {

    private val viewModel by viewModels<ReportsViewModel>()
    private lateinit var binding: FragmentReportsBinding
    private var reportAdapter = BaseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportsBinding.inflate(inflater, container, false)

        viewModel.getContentReport()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reportsRcView.adapter = reportAdapter
        observableData()
    }

    private fun observableData(){
        viewModel.reports.observe(viewLifecycleOwner){
            reportAdapter.submitList(it)
        }
    }
}