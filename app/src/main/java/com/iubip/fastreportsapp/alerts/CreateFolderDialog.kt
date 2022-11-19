package com.iubip.fastreportsapp.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.iubip.fastreportsapp.databinding.RenameDialogBinding
import com.iubip.fastreportsapp.fragments.templates.TemplateViewModel
import com.iubip.fastreportsapp.model.Rename
import com.iubip.fastreportsapp.preferences.PreferencesStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFolderDialog : DialogFragment() {

    companion object {
        var name = ""
    }

    private lateinit var preferences: PreferencesStore

    private lateinit var binding: RenameDialogBinding
    private val viewModel by viewModels<TemplateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = RenameDialogBinding.inflate(inflater, container, false)
        preferences = PreferencesStore(context)

        binding.next.setOnClickListener {
            val inFolderById = preferences.getSavedId()
            if (inFolderById != "") {
                viewModel.createFolder(inFolderById!!,
                    (Gson().toJson(Rename(binding.nameFileNew.text.toString()))))
                preferences.saveSelectedId("")
                dismiss()
            } else {
                viewModel.createFolder(PreferencesStore.SELECTED_ID,
                    (Gson().toJson(Rename(binding.nameFileNew.text.toString()))))
                dismiss()
            }
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