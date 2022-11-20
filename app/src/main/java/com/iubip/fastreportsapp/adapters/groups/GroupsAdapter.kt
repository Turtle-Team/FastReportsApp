package com.iubip.fastreportsapp.adapters.groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.databinding.ItemFolderExportBinding
import com.iubip.fastreportsapp.databinding.ItemGroupsBinding
import com.iubip.fastreportsapp.fragments.Diffutils
import com.iubip.fastreportsapp.model.ExportFile

data class Group(
    val title: String
)

class GroupsAdapter() :
    ListAdapter<Group, GroupsAdapter.GroupHolder>(Diffutils()) {

    class GroupHolder(private val binding: ItemGroupsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Group) {
            binding.groupTextView.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        return GroupsAdapter.GroupHolder(
            ItemGroupsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.bind(currentList[position])
    }

}