package com.iubip.fastreportsapp.fragments.additionally

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.alerts.ExportDialog
import com.iubip.fastreportsapp.databinding.FragmentAdditionallyBinding
import com.iubip.fastreportsapp.utils.Download
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdditionallyFragment : Fragment() {

    private lateinit var binding: FragmentAdditionallyBinding
    private val viewModel by viewModels<AdditionallyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdditionallyBinding.inflate(inflater, container, false)

        viewModel.downloadTemplateFile()


        binding.groupButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_groupsFragment)
        }

        binding.userButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_usersFragment)
        }

        binding.apikeyButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_apiKeysFragment)
        }

        binding.downloadButton.setOnClickListener{
            Log.e("RETROFIT", viewModel.response.value.toString())
//            Download().saveFile(viewModel.response.value.toString(), )
        }

        return binding.root
    }
}