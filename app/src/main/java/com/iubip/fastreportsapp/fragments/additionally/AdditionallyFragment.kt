package com.iubip.fastreportsapp.fragments.additionally

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentAdditionallyBinding
import com.iubip.fastreportsapp.databinding.FragmentReportsBinding
import dagger.hilt.android.AndroidEntryPoint

class AdditionallyFragment : Fragment() {

    private lateinit var binding: FragmentAdditionallyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdditionallyBinding.inflate(inflater, container, false)

        binding.groupButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_groupsFragment)
        }

        binding.userButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_usersFragment)
        }

        binding.apikeyButton.setOnClickListener {
            findNavController().navigate(R.id.action_additionallyFragment_to_apiKeysFragment)
        }

        return binding.root
    }
}