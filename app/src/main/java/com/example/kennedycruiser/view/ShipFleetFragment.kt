package com.example.kennedycruiser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kennedycruiser.common.updateError
import com.example.kennedycruiser.common.updateLoading
import com.example.kennedycruiser.databinding.ShipFleetFragmentBinding
import com.example.kennedycruiser.model.AppState
import com.example.kennedycruiser.model.Repository
import com.example.kennedycruiser.model.ShipFleet
import com.example.kennedycruiser.view.adapter.ShipAdapter
import com.example.kennedycruiser.viewmodel.ShipViewModel
import org.koin.android.ext.android.inject

class ShipFleetFragment: Fragment() {

    private val viewModel: ShipViewModel by inject<ShipViewModel>()
    private var _binding: ShipFleetFragmentBinding? = null
    private val binding: ShipFleetFragmentBinding get() = _binding!!
    private val adapter: ShipAdapter by lazy {
        ShipAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = ShipFleetFragmentBinding.inflate(inflater, container, false)
        initViews()
        initObservables()
        return binding.root
    }

    private fun initViews() {
        binding.shipFleet.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.shipFleet.adapter = adapter
    }

    private fun initObservables() {
        viewModel.shipFleet.observe(viewLifecycleOwner){
            when (it){
                is AppState.Response -> updateAdapter(it.data)
                is AppState.Error -> updateError(it.error)
                is AppState.Loading -> updateLoading(it.isLoading)
            }
        }
    }

    private fun updateLoading(loading: Boolean) {
        activity?.updateLoading(loading)
    }

    private fun updateError(error: String) {
        activity?.updateError(error)
    }

    private fun updateAdapter(data: ShipFleet) {
        adapter.submitList(data)
    }
}