package com.rahulraghuwanshi.mystartheros.presentation.characters_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rahulraghuwanshi.mystartheros.databinding.ItemCharacterDetailBinding
import com.rahulraghuwanshi.mystartheros.domain.model.Character

class CharactersAdapter(
    private val onClick: (Character) -> Unit
) :
    PagingDataAdapter<Character, CharacterPosterViewHolder>(CharacterDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterPosterViewHolder {
        return CharacterPosterViewHolder(
            ItemCharacterDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterPosterViewHolder, position: Int) {
        holder.bind(getItem(position), onClick = onClick)
    }
}

class CharacterDiffCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}

class CharacterPosterViewHolder(
    val binding: ItemCharacterDetailBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        character: Character?,
        onClick: (Character) -> Unit
    ) {
        character?.let {
            binding.root.setOnClickListener { onClick.invoke(character) }
            binding.tvName.text = character.name
            binding.tvGender.text = character.gender.uppercase()
            binding.tvHeight.text = character.height
        }
    }
}