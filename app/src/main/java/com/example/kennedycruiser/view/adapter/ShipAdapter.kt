package com.example.kennedycruiser.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kennedycruiser.BuildConfig
import com.example.kennedycruiser.R
import com.example.kennedycruiser.databinding.ShipItemLayoutBinding
import com.example.kennedycruiser.model.ShipResponse

private const val TAG = "ShipAdapter"
class ShipAdapter : ListAdapter<ShipResponse, ShipAdapter.ShipViewHolder>(ShipDiffUtil) {

    object ShipDiffUtil : DiffUtil.ItemCallback<ShipResponse>() {
        override fun areItemsTheSame(oldItem: ShipResponse, newItem: ShipResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShipResponse, newItem: ShipResponse): Boolean {
            return oldItem == newItem
        }
    }

    class ShipViewHolder(private val binding: ShipItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: ShipResponse) {
            binding.shipName.text = binding.root.context.getString(
                R.string.ship_name_detail, dataItem.shipName
            )
            binding.shipCrew.text = binding.root.context.getString(
                R.string.ship_crew_detail, dataItem.shipFacts.crew
            )
            binding.shipPassengerCapacity.text = binding.root.context.getString(
                R.string.ship_passenger_detail, dataItem.shipFacts.passengerCapacity
            )
            binding.shipDate.text = binding.root.context.getString(
                R.string.ship_inaugural_detail, dataItem.shipFacts.inauguralDate
            )
            Log.d(TAG, "onBind: url = ${BuildConfig.BASE_URL}${dataItem.bgeImagePath}")
            Glide.with(binding.root.context).load(
                "${BuildConfig.BASE_IMAGE}${dataItem.bgeImagePath}"
            ).into(binding.shipImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        return ShipViewHolder(
            ShipItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}