package com.iubip.fastreportsapp.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.iubip.fastreportsapp.databinding.RenameDialogBinding
import com.iubip.fastreportsapp.fragments.exports.ExportsViewModel
import com.iubip.fastreportsapp.fragments.reports.ReportsViewModel
import com.iubip.fastreportsapp.model.Rename
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RenameDialogExport : DialogFragment() {

    companion object {
        var nameExports = ""
    }

    private lateinit var binding: RenameDialogBinding
    private val viewModel by viewModels<ExportsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = RenameDialogBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener {
            viewModel.renameFile(nameExports, (Gson().toJson(Rename(binding.nameFileNew.text.toString()))))
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}