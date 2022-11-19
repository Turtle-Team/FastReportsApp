package com.iubip.fastreportsapp.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import com.iubip.fastreportsapp.databinding.ExportDialogBinding
import com.iubip.fastreportsapp.utils.Constants

class ExportDialog : DialogFragment() {

    private lateinit var binding: ExportDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExportDialogBinding.inflate(inflater, container, false)
        binding.selectExport.setOnClickListener {
            showPopupMenu(binding.selectExport)
        }
        binding.next.setOnClickListener {
            FolderExportDialog.format = binding.selectExport.text.toString()
            FolderExportDialog().show(parentFragmentManager, "FolderExportDialog")
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(view.context, view)
        popup.menu.apply {
            for (n in Constants.FORMATS_EXPORT) {
                add(n).setOnMenuItemClickListener {
                    binding.selectExport.text = n
                    true
                }
            }
        }
        popup.show()
    }
}