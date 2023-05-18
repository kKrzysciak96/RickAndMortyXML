package com.example.rickandmortyxml.features.characters.presentation.whole

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.databinding.CharacterItemBinding
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable

class CharacterAdapter() :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters: MutableList<CharacterDisplayable> = mutableListOf()
    private var listener: ((CharacterDisplayable) -> Unit)? = null

    fun setCharacters(characters: List<CharacterDisplayable>) {
        if (characters.isNotEmpty()) this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()

    }

    fun setOnClickListener(listener: (CharacterDisplayable) -> Unit) {
        this.listener = listener
    }

    inner class CharacterViewHolder(view: View) : ViewHolder(view) {
        private val binding: CharacterItemBinding = CharacterItemBinding.bind(view)
        fun bind(character: CharacterDisplayable) {
            with(binding) {
                characterName.text = character.name
                listener?.let { listener -> root.setOnClickListener { listener(character) } }
                Glide.with(root)
                    .load(character.image)
                    .into(characterImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val singleCharacter = characters[position]
        holder.bind(singleCharacter)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}