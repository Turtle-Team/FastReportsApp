package com.iubip.fastreportsapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.databinding.ItemBaseBinding

class BaseAdapter() : ListAdapter<BaseItemType, RecyclerView.ViewHolder>(Diffutils()) {

    class FolderViewHolder(private val binding: ItemBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseItemType.Folder) {

            binding.dateView.text = item.createdTime
            binding.sizeView.text = item.size.toString()

        }

    }

    class FileViewHolder(private val binding: ItemBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseItemType.File) {
            binding.dateView.text = item.createdTime
            binding.sizeView.text = item.size.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> FileViewHolder(ItemBaseBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            2 -> FolderViewHolder(ItemBaseBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            else -> throw java.lang.IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FileViewHolder -> holder.bind(currentList[position] as BaseItemType.File)
            is FolderViewHolder -> holder.bind(currentList[position] as BaseItemType.Folder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is BaseItemType.File -> 1
            is BaseItemType.Folder -> 2
        }
    }
}

sealed class BaseItemType() {
    data class File(
        val createdTime: String,
        val editedTime: String,
        val id: String,
        val name: String,
        val size: Int,
        val status: String,
        val statusReason: String,
        val type: String,
    ) : BaseItemType()

    data class Folder(
        val createdTime: String,
        val editedTime: String,
        val id: String,
        val name: String,
        val size: Int,
        val status: String,
        val statusReason: String,
        val type: String,
    ) : BaseItemType()
}