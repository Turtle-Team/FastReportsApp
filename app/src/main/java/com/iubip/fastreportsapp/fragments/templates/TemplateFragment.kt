package com.iubip.fastreportsapp.fragments.templates

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentTemplateBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.utils.Animations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TemplateFragment : Fragment() {

    private val viewModel by viewModels<TemplateViewModel>()
    private lateinit var binding: FragmentTemplateBinding
    private var templateAdapter = BaseAdapter(
        onClick = { clickCard(it) },
        deleteFolderClick = { deleteFolder(it) },
        deleteFileClick = { deleteFile(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTemplateBinding.inflate(inflater, container, false)

        viewModel.getContentFolder()

        binding.floatingButton.popupButton.setOnClickListener {
            if (binding.floatingButton.createFolderButton.isVisible) {
                Animations().showButtons(false, binding.floatingButton.createFolderButton)
                Animations().showButtons(false, binding.floatingButton.createTemplateButton)
                Animations().showButtons(false, binding.floatingButton.donwloadTemplateButton)
            } else {
                Animations().showButtons(true, binding.floatingButton.createFolderButton)
                Animations().showButtons(true, binding.floatingButton.createTemplateButton)
                Animations().showButtons(true, binding.floatingButton.donwloadTemplateButton)            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.templatesRcView.adapter = templateAdapter
        observableData()
    }

    private fun observableData() {
        viewModel.response.observe(viewLifecycleOwner) {
            templateAdapter.submitList(it)
        }
    }

    fun clickCard(item: BaseItemType.File) {
        findNavController().navigate(
            R.id.action_templateFragment_to_folderItemFragment,
            bundleOf("aaa" to item.id)
        )
    }

    fun deleteFolder(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFolder(item)
            delay(500)
            viewModel.getContentFolder()
        }
    }

    fun deleteFile(item: String) {
        lifecycleScope.launch {
            viewModel.deleteFile(item)
            delay(500)
            viewModel.getContentFolder()
        }
    }
}
