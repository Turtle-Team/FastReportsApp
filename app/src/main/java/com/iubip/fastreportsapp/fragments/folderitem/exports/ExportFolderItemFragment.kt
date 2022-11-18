package com.iubip.fastreportsapp.fragments.folderitem.exports

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.databinding.FragmentExportFolderItemBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExportFolderItemFragment : Fragment() {

    private val viewModel by viewModels<ExportFolderItemViewModel>()
    private lateinit var binding: FragmentExportFolderItemBinding
    private var folderAdapter = BaseAdapter(
        onClick = { clickCard(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExportFolderItemBinding.inflate(inflater, container, false)

        viewModel.idFolder = arguments?.getString("aaa")
        Log.e("FOLDER ID", viewModel.toString())

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
}