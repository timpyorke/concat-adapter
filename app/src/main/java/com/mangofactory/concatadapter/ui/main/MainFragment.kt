package com.mangofactory.concatadapter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.mangofactory.concatadapter.databinding.FragmentMainBinding
import com.mangofactory.concatadapter.ui.HorizontalAdapter
import com.mangofactory.concatadapter.ui.HorizontalWrapperAdapter
import com.mangofactory.concatadapter.ui.OneColumnAdapter
import com.mangofactory.concatadapter.ui.TwoColumnAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val oneColumnAdapter: OneColumnAdapter by lazy {
        OneColumnAdapter { onOneColumnItemClicked(it) }
    }

    private val horizontalAdapter: HorizontalAdapter by lazy {
        HorizontalAdapter { onHorizontalItemClicked(it) }
    }

    private val horizontalWrapperAdapter: HorizontalWrapperAdapter by lazy {
        HorizontalWrapperAdapter(horizontalAdapter)
    }

    private val twoColumnAdapter: TwoColumnAdapter by lazy {
        TwoColumnAdapter { onTwoColumnItemClicked(it) }
    }

    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        ConcatAdapter(config, oneColumnAdapter, horizontalWrapperAdapter, twoColumnAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let { horizontalWrapperAdapter.onRestoreState(it) }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.oneLiveData.observe(viewLifecycleOwner, {
            oneColumnAdapter.updateData(it)
        })

        viewModel.horizontalLiveData.observe(viewLifecycleOwner, {
            horizontalAdapter.updateData(it)
        })

        viewModel.twoLiveData.observe(viewLifecycleOwner, {
            twoColumnAdapter.updateData(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = GridLayoutManager(context, 12)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    OneColumnAdapter.VIEW_TYPE -> 12
                    TwoColumnAdapter.VIEW_TYPE -> 6
                    HorizontalWrapperAdapter.VIEW_TYPE -> 12
                    else -> 12
                }
            }
        }
        binding.apply {
            recyclerViewMain.apply {
                layoutManager = mLayoutManager
                adapter = concatAdapter
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        horizontalWrapperAdapter.onSaveState(outState)
    }

    private fun onOneColumnItemClicked(msg: String) {
        mToast(msg, "one column")
    }

    private fun onHorizontalItemClicked(msg: String) {
        mToast(msg, "horizontal")
    }

    private fun onTwoColumnItemClicked(msg: String) {
        mToast(msg, "two column")
    }

    private fun mToast(msg: String, s: String) {
        Toast.makeText(context, "$msg from $s was clicked", Toast.LENGTH_SHORT).show()
    }
}