package com.iubip.fastreportsapp.fragments.folderitem.reports

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentFolderItemBinding
import com.iubip.fastreportsapp.databinding.FragmentReportFolderItemBinding
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.fragments.folderitem.templates.FolderItemViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportFolderItemFragment : Fragment() {

    private val viewModel by viewModels<ReportFolderItemViewModel>()
    private lateinit var binding: FragmentReportFolderItemBinding
    private var folderAdapter = BaseAdapter(
        onClick = {clickCard(it)}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportFolderItemBinding.inflate(inflater, container, false)

        viewModel.idFolder = arguments?.getString("aaa")
        Log.e("FOLDER ID", viewModel.toString())

        viewModel.getFolderById(viewModel.idFolder.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.folderReportRcView.adapter = folderAdapter
        observableData()
    }

    private fun observableData(){
        viewModel.folders.observe(viewLifecycleOwner){
            folderAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File){
        viewModel.getFolderById(item.id)
    }
}