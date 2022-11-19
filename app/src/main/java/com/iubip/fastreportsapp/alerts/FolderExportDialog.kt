package com.iubip.fastreportsapp.alerts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.iubip.fastreportsapp.adapters.alerts.FolderExportAdapter
import com.iubip.fastreportsapp.databinding.FolderExportDialogBinding
import com.iubip.fastreportsapp.model.ExportFile
import com.iubip.fastreportsapp.model.FileToExport
import com.iubip.fastreportsapp.utils.Animations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderExportDialog : DialogFragment() {
    private lateinit var binding: FolderExportDialogBinding
    private val viewModel by viewModels<FolderExportViewModel>()
    private var folderExportAdapter = FolderExportAdapter(onClick = { clickOnFolder(it) })

    companion object {
        var format = ""
        var fileId = ""
        var namefile = ""
        var idssss = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FolderExportDialogBinding.inflate(inflater, container, false)

        viewModel.getFolders()
        binding.folderExportRcView.adapter = folderExportAdapter
        observableData()

        binding.exportButton.setOnClickListener {


            viewModel.exportInThisFolder(fileId,
                Gson().toJson(FileToExport(namefile,
                    viewModel.idFolder,
                    format,
                    "en-GB")))
            viewModel.getFolderById(idssss)

        }

        return binding.root
    }

    private fun clickOnFolder(item: ExportFile) {
        viewModel.idFolder = item.id
        idssss = item.id
        viewModel.nameFolder = item.name
        viewModel.getFolderById(item.id)
    }

    private fun observableData() {
        viewModel.response.observe(viewLifecycleOwner) {
            folderExportAdapter.submitList(it.files)
            Log.e("Logger", it.files.toString())
            if (viewModel.idFolder != "") {
                binding.exportButton.text = "Экспортировать в ${viewModel.nameFolder}"
                Animations().showButtons(true, binding.exportButton)
            } else {
                Animations().showButtons(false, binding.exportButton)
            }
        }
    }

    override fun onStart() {
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }
}
