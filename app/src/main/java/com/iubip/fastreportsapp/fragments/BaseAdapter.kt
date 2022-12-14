package com.iubip.fastreportsapp.fragments

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.R
import com.iubip.fastreportsapp.databinding.ItemBaseBinding

class BaseAdapter(
    private val onClick: (item: BaseItemType.File) -> Unit,
    private val deleteFolderClick: (item: String) -> Unit,
    private val deleteFileClick: (item: String) -> Unit,
    private val exportFile: (item: BaseItemType.Folder) -> Unit,
    private val renameFile: (item: String) -> Unit,
    private val startWebView: () -> Unit,
) : ListAdapter<BaseItemType, RecyclerView.ViewHolder>(Diffutils()) {

    class FileViewHolder(
        private val binding: ItemBaseBinding,
        private val deleteFileClick: (item: String) -> Unit,
        private val exportFile: (item: BaseItemType.Folder) -> Unit,
        private val renameFile: (item: String) -> Unit,
        private val startWebView: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: BaseItemType.Folder) {
            binding.icon.setImageResource(R.drawable.ic_file)
            binding.name.text = item.name

            binding.itemCard.setOnLongClickListener {
                showPopup(binding.itemCard, item)
                return@setOnLongClickListener true
            }

            binding.itemCard.setOnClickListener {
                startWebView()
                Log.e("WEB", "YES")
            }

            val date = item.editedTime
            binding.dateView.text = date.substring(0..9)

            binding.sizeView.text = item.size.toString()
        }

        private fun showPopup(view: View, item1: BaseItemType.Folder) {
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.file_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item?.itemId) {
                    R.id.renameFile -> {
                        renameFile(item1.id)
                    }
                    R.id.exportFile -> {
                        exportFile(item1)
                    }
                    R.id.copyFile -> {

                    }
                    R.id.deleteFile -> {
                        deleteFileClick(item1.id)
                    }
                }
                true
            }
            popup.show()
        }
    }


    class FolderViewHolder(
        private val binding: ItemBaseBinding,
        private val onClick: (item: BaseItemType.File) -> Unit,
        private val deleteFolderClick: (item: String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: BaseItemType.File) {

            binding.icon.setImageResource(R.drawable.ic_folder)
            binding.name.text = item.name
            val date = item.editedTime
            binding.dateView.text = date.substring(0..9)

            binding.itemCard.setOnClickListener {
                onClick(item)
            }

            binding.itemCard.setOnLongClickListener {
                showPopup(binding.itemCard, item.id)
                return@setOnLongClickListener true
            }
        }

        private fun showPopup(view: View, item1: String) {
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.file_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item?.itemId) {
                    R.id.deleteFile -> {
                        deleteFolderClick(item1)
                    }
                    R.id.renameFile -> {
                    }
                }
                true
            }
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            2 -> FolderViewHolder(
                ItemBaseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick, deleteFolderClick
            )
            1 -> FileViewHolder(
                ItemBaseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                deleteFileClick, exportFile, renameFile, startWebView
            )
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
