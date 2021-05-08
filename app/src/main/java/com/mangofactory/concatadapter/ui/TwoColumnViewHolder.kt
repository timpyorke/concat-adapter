package com.mangofactory.concatadapter.ui

import androidx.recyclerview.widget.RecyclerView
import com.mangofactory.concatadapter.databinding.ViewTwoColumnBinding

class TwoColumnViewHolder(
    private val binding: ViewTwoColumnBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, onClick: (String) -> Unit) {
        binding.apply {
            textView.text = data
            root.setOnClickListener { onClick(data) }
        }
    }
}