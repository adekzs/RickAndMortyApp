package com.adeks.rickandmortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.adeks.rickandmortyapp.databinding.CharacterItemBinding
import com.adeks.rickandmortyapp.model.Character

class CharacterAdapter(private val characters : List<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class CharacterViewHolder(private val binding : CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character : Character) {
            binding.characterImage.load(character.image)
            binding.characterNameTv.text = character.name
            binding.characterSpecieTv.text = character.species
            binding.characterStatusTv.text = character.status
        }
    }
}