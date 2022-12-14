package com.iubip.fastreportsapp.fragments.exports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.alerts.CreateFolderDialog
import com.iubip.fastreportsapp.alerts.FolderExportDialog
import com.iubip.fastreportsapp.alerts.RenameDialog
import com.iubip.fastreportsapp.alerts.RenameDialogExport
import com.iubip.fastreportsapp.databinding.FragmentExportsBinding
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.fragments.reports.ReportsViewModel
import com.iubip.fastreportsapp.utils.Animations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExportsFragment : Fragment() {

    private val viewModel by viewModels<ExportsViewModel>()
    private lateinit var binding: FragmentExportsBinding
    private var exportAdapter = BaseAdapter(
        onClick = { clickCard(it) },
        deleteFolderClick = { deleteFolder(it) },
        deleteFileClick = { deleteFile(it) },
        exportFile = {exportFile(it)},
        renameFile = {renameFile(it)},
        startWebView = {startWebView()}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExportsBinding.inflate(inflater, container, false)

        viewModel.getContentExport()

        binding.floatingButton.popupButton.setOnClickListener {
            if (binding.floatingButton.createFolderButton.isVisible) {
                Animations().showButtons(false, binding.floatingButton.createFolderButton)
            } else {
                Animations().showButtons(true, binding.floatingButton.createFolderButton)

                binding.floatingButton.createFolderButton.setOnClickListener {
                    CreateFolderDialog().show(parentFragmentManager, "Create Folder")
                }
            }
        }

        observableData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exportRcView.adapter = exportAdapter
        observableData()
    }

    private fun observableData() {
        viewModel.exports.observe(viewLifecycleOwner) {
            exportAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File) {
        findNavController().navigate(
            R.id.action_exportsFragment_to_exportFolderItemFragment,
            bundleOf("aaa" to item.id)
        )
    }

    fun deleteFolder(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFolder(item)
            delay(500)
            viewModel.getContentExport()
        }
    }

    fun deleteFile(item: String){
        lifecycleScope.launch {
            viewModel.deleteFile(item)
            delay(500)
            viewModel.getContentExport()
        }
    }

    fun exportFile(item: BaseItemType.Folder){
    }

    fun renameFile(item: String) {
        RenameDialogExport.nameExports = item
        RenameDialogExport().show(parentFragmentManager, "Rename file")
    }

    fun startWebView(){
        findNavController().navigate(R.id.action_exportsFragment_to_webViewFragment)
    }
}