package com.iubip.fastreportsapp.fragments

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.ItemBaseBinding
import java.time.LocalTime

class BaseAdapter() : ListAdapter<BaseItemType, RecyclerView.ViewHolder>(Diffutils()) {

    class FileViewHolder(private val binding: ItemBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: BaseItemType.Folder) {
            binding.icon.setImageResource(R.drawable.ic_file)
            binding.name.text = item.name
            binding.dateView.text = item.editedTime
            binding.sizeView.text = item.size.toString()
        }
    }

    class FolderViewHolder(private val binding: ItemBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseItemType.File) {

            binding.icon.setImageResource(R.drawable.ic_folder)
            binding.name.text = item.name
            binding.dateView.text = item.createdTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            2 -> FolderViewHolder(ItemBaseBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            1 -> FileViewHolder(ItemBaseBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            else -> throw java.lang.IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FolderViewHolder -> holder.bind(currentList[position] as BaseItemType.File)
            is FileViewHolder -> holder.bind(currentList[position] as BaseItemType.Folder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is BaseItemType.File -> 2
            is BaseItemType.Folder -> 1
        }
    }
}

sealed class BaseItemType() {
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
}