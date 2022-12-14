package com.iubip.fastreportsapp.fragments.folderitem.exports

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.alerts.RenameDialog
import com.iubip.fastreportsapp.databinding.FragmentExportFolderItemBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExportFolderItemFragment : Fragment() {

    private val viewModel by viewModels<ExportFolderItemViewModel>()
    private lateinit var binding: FragmentExportFolderItemBinding
    private var folderAdapter = BaseAdapter(
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
        binding = FragmentExportFolderItemBinding.inflate(inflater, container, false)

//        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
//        activity?.actionBar?.setDisplayShowHomeEnabled(true)

        viewModel.idFolder = arguments?.getString("aaa")
        Log.e("FOLDER ID", viewModel.toString())

//        activity?.actionBar?.title= viewModel.idFolder.toString()

        viewModel.getFolderById(viewModel.idFolder.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.folderExportsRcView.adapter = folderAdapter
        observableData()
    }

    private fun observableData() {
        viewModel.folders.observe(viewLifecycleOwner) {
            folderAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File) {
        viewModel.getFolderById(item.id)
    }

    fun deleteFolder(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFolder(item)
            delay(500)
            viewModel.getFolderById(id = viewModel.idFolder.toString())
        }
    }

    fun deleteFile(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFile(item)
            delay(500)
            viewModel.getFolderById(id = viewModel.idFolder.toString())
        }
    }

    fun exportFile(item: BaseItemType.Folder){

    }

    fun renameFile(item: String){
        RenameDialog().show(parentFragmentManager, "Rename file")
    }

    fun startWebView(){
        findNavController().navigate(R.id.action_exportFolderItemFragment_to_webViewFragment)
    }
}