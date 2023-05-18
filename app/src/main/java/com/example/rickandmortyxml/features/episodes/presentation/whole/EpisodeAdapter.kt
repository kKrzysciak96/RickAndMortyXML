package com.example.rickandmortyxml.features.episodes.presentation.whole

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.databinding.EpisodeItemBinding
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val episodes: MutableList<EpisodeDisplayable> = mutableListOf()
    private var listener: ((EpisodeDisplayable) -> Unit)? = null


    fun setEpisodes(episodes: List<EpisodeDisplayable>) {
        if (episodes.isNotEmpty()) this.episodes.clear()

        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (EpisodeDisplayable) -> Unit) {
        this.listener = listener
    }

    inner class EpisodeViewHolder(view: View) : ViewHolder(view) {
        private val binding: EpisodeItemBinding = EpisodeItemBinding.bind(view)

        fun bind(episode: EpisodeDisplayable) {
            with(binding) {
                episodeName.text = episode.name
                listener?.let { listener -> root.setOnClickListener { listener(episode) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.episode_item, parent, false)
        return EpisodeViewHolder(view)
    }


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val singleEpisode = episodes[position]
        holder.bind(singleEpisode)

    }

    override fun getItemCount(): Int {
        return episodes.size
    }

}