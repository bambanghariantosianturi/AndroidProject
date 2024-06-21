package com.example.myandroidproject.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.TypePokemonModel
import com.example.myandroidproject.detail.databinding.ItemListAbilityBinding

class TypeAdapter : RecyclerView.Adapter<TypeAdapter.ListViewHolder>() {
    private var listData = ArrayList<TypePokemonModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setDataItems(data: List<TypePokemonModel>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.example.myandroidproject.detail.R.layout.item_list_ability,
                parent,
                false
            )
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
        private val binding = ItemListAbilityBinding.bind(itemView)
        fun bind(data: TypePokemonModel) {
            binding.tvName.text = data.type?.name.orEmpty()
        }
    }
}