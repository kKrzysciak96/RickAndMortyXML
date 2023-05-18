package com.example.rickandmortyxml.features.locations.presentation.whole

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.databinding.LocationItemBinding
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    val locations: MutableList<LocationDisplayable> = mutableListOf()
    var listener: ((LocationDisplayable) -> Unit)? = null
    fun setLocations(locations: List<LocationDisplayable>) {
        if (locations.isNotEmpty()) this.locations.clear()
        this.locations.addAll(locations)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (LocationDisplayable) -> Unit) {
        this.listener = listener
    }

    inner class LocationViewHolder(view: View) : ViewHolder(view) {
        private val binding: LocationItemBinding = LocationItemBinding.bind(view)
        fun bind(location: LocationDisplayable) {
            with(binding) {
                locationName.text = location.name
                listener?.let { listener -> root.setOnClickListener { listener(location) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.location_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val singleLocation = locations[position]
        holder.bind(singleLocation)
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}