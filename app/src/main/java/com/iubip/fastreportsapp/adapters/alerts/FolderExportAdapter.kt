package com.iubip.fastreportsapp.adapters.alerts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.databinding.ItemFolderExportBinding
import com.iubip.fastreportsapp.fragments.Diffutils
import com.iubip.fastreportsapp.model.ContentFile

class FolderExportAdapter(private val onClick: (item: ContentFile) -> Unit) :
    ListAdapter<ContentFile, FolderExportAdapter.FolderHolder>(Diffutils()) {

    class FolderHolder(
        private val binding: ItemFolderExportBinding,
        private val onClick: (item: ContentFile) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContentFile) {
            binding.title.text = item.name
            if (item.type == "File") {
                binding.itemFolderExport.visibility = View.GONE
            }
            binding.itemFolderExport.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderHolder {
        return FolderExportAdapter.FolderHolder(ItemFolderExportBinding.inflate(LayoutInflater.from(
            parent.context), parent, false), onClick)
    }

    override fun onBindViewHolder(holder: FolderHolder, position: Int) {
        holder.bind(currentList[position])
    }

}