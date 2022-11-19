package com.iubip.fastreportsapp.adapters.apikeys

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iubip.fastreportsapp.databinding.ItemApikeyBinding
import com.iubip.fastreportsapp.fragments.Diffutils
import com.iubip.fastreportsapp.model.ApiKey

class ApiKeyAdapter : ListAdapter<ApiKey, ApiKeyAdapter.ApiKeyHolder>(Diffutils()) {

    class ApiKeyHolder(private val binding: ItemApikeyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ApiKey) {
            binding.keyTextView.text = item.value
            binding.descriptionTextView.text = item.description
            binding.dateTextView.text = item.expired
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiKeyHolder {
        return ApiKeyAdapter.ApiKeyHolder(
            ItemApikeyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ApiKeyHolder, position: Int) {
        holder.bind(currentList[position])
    }

}