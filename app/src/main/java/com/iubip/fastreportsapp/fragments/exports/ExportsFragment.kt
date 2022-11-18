package com.iubip.fastreportsapp.fragments.exports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentExportsBinding
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.fragments.reports.ReportsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExportsFragment : Fragment() {

    private val viewModel by viewModels<ExportsViewModel>()
    private lateinit var binding: FragmentExportsBinding
    private var exportAdapter = BaseAdapter(
        onClick = {clickCard(it)}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExportsBinding.inflate(inflater, container, false)

        viewModel.getContentExport()

        observableData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exportRcView.adapter = exportAdapter
        observableData()
    }

    private fun observableData(){
        viewModel.exports.observe(viewLifecycleOwner){
            exportAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File){
        findNavController().navigate(R.id.action_exportsFragment_to_folderItemFragment, bundleOf("aaa" to item.id))
    }
}