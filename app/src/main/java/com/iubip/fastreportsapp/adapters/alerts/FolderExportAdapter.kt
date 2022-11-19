package com.iubip.fastreportsapp.adapters.alerts

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.databinding.FolderExportDialogBinding
import com.iubip.fastreportsapp.databinding.ItemBaseBinding
import com.iubip.fastreportsapp.databinding.ItemFolderExportBinding
import com.iubip.fastreportsapp.fragments.BaseAdapter
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.fragments.Diffutils
import com.iubip.fastreportsapp.model.ContentFile
import com.iubip.fastreportsapp.model.File

class FolderExportAdapter : ListAdapter<ContentFile, FolderExportAdapter.FolderHolder>(Diffutils()) {

    class FolderHolder(private val binding: ItemFolderExportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContentFile) {
            binding.title.text = item.name
            if (item.type == "File") {
                binding.itemFolderExport.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderHolder {
        return FolderExportAdapter.FolderHolder(ItemFolderExportBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: FolderHolder, position: Int) {
        holder.bind(currentList[position])
    }

}