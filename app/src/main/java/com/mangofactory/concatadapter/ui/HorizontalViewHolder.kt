package com.mangofactory.concatadapter.ui

import androidx.recyclerview.widget.RecyclerView
import com.mangofactory.concatadapter.databinding.ViewHorizontalBinding

class HorizontalViewHolder(
    private val binding: ViewHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, onClick: (String) -> Unit) {
        binding.apply {
            textView.text = data
            root.setOnClickListener { onClick(data) }
        }
    }
}