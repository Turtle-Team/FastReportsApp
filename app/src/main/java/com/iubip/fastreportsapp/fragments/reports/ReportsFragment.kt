package com.iubip.fastreportsapp.fragments.reports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.alerts.RenameDialog
import com.iubip.fastreportsapp.alerts.RenameDialogReport
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import com.iubip.fastreportsapp.databinding.FragmentTemplateBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.fragments.templates.TemplateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReportsFragment : Fragment() {

    private val viewModel by viewModels<ReportsViewModel>()
    private lateinit var binding: FragmentReportsBinding
    private var reportAdapter = BaseAdapter(
        onClick = { clickCard(it) },
        deleteFolderClick = { deleteFolder(it) },
        deleteFileClick = { deleteFile(it) },
        exportFile = {exportFile(it)},
        renameFile = {renameFile(it)}
    )

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

    private fun observableData() {
        viewModel.reports.observe(viewLifecycleOwner) {
            reportAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File) {
        findNavController().navigate(
            R.id.action_reportsFragment_to_reportFolderItemFragment,
            bundleOf("aaa" to item.id)
        )
    }

    fun deleteFolder(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFolder(item)
            delay(500)
            viewModel.getContentReport()
        }
    }

    fun deleteFile(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFile(item)
            delay(500)
            viewModel.getContentReport()
        }
    }

    fun exportFile(item: String){

    }

    fun renameFile(item: String) {
        RenameDialogReport.nameReports = item
        RenameDialogReport().show(parentFragmentManager, "Rename file")
    }
}