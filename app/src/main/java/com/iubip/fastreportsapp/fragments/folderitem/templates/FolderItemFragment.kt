package com.iubip.fastreportsapp.fragments.folderitem.templates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.iubip.fastreportsapp.alerts.CreateFolderDialog
import com.iubip.fastreportsapp.alerts.RenameDialog
import com.iubip.fastreportsapp.databinding.FragmentFolderItemBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.preferences.PreferencesStore
import com.iubip.fastreportsapp.utils.Animations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FolderItemFragment : Fragment() {
    private val viewModel by viewModels<FolderItemViewModel>()
    private lateinit var binding: FragmentFolderItemBinding
    private var folderAdapter = BaseAdapter(
        onClick = { clickCard(it) },
        deleteFolderClick = { deleteFolder(it) },
        deleteFileClick = { deleteFile(it) },
        exportFile = { exportFile(it) },
        renameFile = { renameFile(it) }
    )

    private lateinit var preferences: PreferencesStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFolderItemBinding.inflate(inflater, container, false)
        preferences = PreferencesStore(context)
        viewModel.idFolder = arguments?.getString("aaa")!!

        viewModel.getFolderById(viewModel.idFolder.toString())


        binding.floatingButton.popupButton.setOnClickListener {
            if (binding.floatingButton.createFolderButton.isVisible) {
                Animations().showButtons(false, binding.floatingButton.createFolderButton)
            } else {
                Animations().showButtons(true, binding.floatingButton.createFolderButton)

                binding.floatingButton.createFolderButton.setOnClickListener {
                    preferences.saveSelectedId(viewModel.idFolder)
                    CreateFolderDialog().show(parentFragmentManager, "Create Folder")
                }
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.folderRcView.adapter = folderAdapter
        observableData()
    }

    private fun observableData() {
        viewModel.folders.observe(viewLifecycleOwner) {
            folderAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File) {
        preferences.saveSelectedId(item.id)
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

    fun exportFile(item: BaseItemType.Folder) {

    }

    fun renameFile(item: String) {
        RenameDialog().show(parentFragmentManager, "Rename file")
    }
}