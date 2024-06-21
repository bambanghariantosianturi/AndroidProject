package com.example.myandroidproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myandroidproject.core.R
import com.example.myandroidproject.core.databinding.ItemListUserBinding
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ItemPokemonModel


class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    private var listData = ArrayList<ItemPokemonModel>()
    var onItemClick: ((ItemPokemonModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDataItems(data: List<ItemPokemonModel>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListUserBinding.bind(itemView)
        fun bind(data: ItemPokemonModel) {
            val numberPokemon = extractNumberFromUrl(data.url)
            binding.tvName.text = data.name
            Glide.with(binding.root.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberPokemon.png")
                .placeholder(R.drawable.ic_movies_24)
                .into(binding.ivIcon)
        }

        private fun extractNumberFromUrl(url: String): Int? {
            val parts = url.split("/")
            return parts.getOrNull(parts.size - 2)?.toIntOrNull()
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}