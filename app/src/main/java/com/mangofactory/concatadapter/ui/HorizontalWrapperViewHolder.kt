package com.mangofactory.concatadapter.ui

import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mangofactory.concatadapter.databinding.ViewHorizontalWrapperBinding

class HorizontalWrapperViewHolder(
    private val binding: ViewHorizontalWrapperBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mAdapter: HorizontalAdapter, lastScrollX: Int, onScrolled: (Int) -> Unit) {
        val context = binding.root.context
        binding.apply {
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = mAdapter
            }
            recyclerView.doOnPreDraw {
                binding.recyclerView.scrollBy(lastScrollX, 0)
            }
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrolled(recyclerView.computeHorizontalScrollOffset())
                }
            })
        }
    }
}