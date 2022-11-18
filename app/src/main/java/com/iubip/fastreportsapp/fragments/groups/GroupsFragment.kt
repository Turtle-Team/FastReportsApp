package com.iubip.fastreportsapp.fragments.groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.FragmentApiKeysBinding
import com.iubip.fastreportsapp.databinding.FragmentGroupsBinding
import com.iubip.fastreportsapp.fragments.apikeys.ApiKeysViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupsFragment : Fragment() {

    private val viewModel by viewModels<GroupsViewModel>()
    private lateinit var binding: FragmentGroupsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupsBinding.inflate(inflater, container, false)

        viewModel.getContentGroups()

        observableData()

        return binding.root
    }

    fun observableData(){
        viewModel.groups.observe(viewLifecycleOwner){
            binding.textGroups.text = it.toString()
        }
    }
}