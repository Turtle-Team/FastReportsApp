package com.iubip.fastreportsapp.alerts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.adapters.alerts.FolderExportAdapter
import com.iubip.fastreportsapp.databinding.ExportDialogBinding
import com.iubip.fastreportsapp.databinding.FolderExportDialogBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.templates.TemplateViewModel
import com.iubip.fastreportsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderExportDialog :DialogFragment() {
    private lateinit var binding: FolderExportDialogBinding
    private val viewModel by viewModels<FolderExportViewModel>()
    private var folderExportAdapter = FolderExportAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FolderExportDialogBinding.inflate(inflater, container, false)

        viewModel.getFolders()
        binding.folderExportRcView.adapter = folderExportAdapter
        observableData()

        return binding.root
    }

    private fun observableData() {
        viewModel.response.observe(viewLifecycleOwner) {
            folderExportAdapter.submitList(it.files)
            Log.e("Logger", it.files.toString())
        }
    }

    override fun onStart() {
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }
}
