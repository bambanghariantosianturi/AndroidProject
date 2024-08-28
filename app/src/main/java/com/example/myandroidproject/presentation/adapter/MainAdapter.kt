package com.example.myandroidproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myandroidproject.core.R
import com.example.myandroidproject.core.databinding.ItemListMovieBinding
import com.example.myandroidproject.core.domain.model.listnewsmodel.ListNewsModel
import com.example.myandroidproject.kit.getTimeAgo


class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    private var listData = ArrayList<ListNewsModel>()
    var onItemClick: ((ListNewsModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDataItems(data: List<ListNewsModel>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(item: CharSequence?) {
        if (item.contentEquals("recent", ignoreCase = true)) {
            listData.sortByDescending { it.timeCreated }
            notifyDataSetChanged()
        } else if (item.contentEquals("popular", ignoreCase = true)) {
            listData.sortBy { it.rank }
            notifyDataSetChanged()
        } else {
            listData
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: ListNewsModel) {
            binding.tvRating.text = data.title
            binding.tvTitleActivity.text = data.description
            binding.tvCreatedDate.text = getTimeAgo(data.timeCreated.toLong())
            Glide.with(binding.root.context)
                .load(data.bannerUrl)
                .placeholder(R.drawable.ic_news)
                .into(binding.ivMovie)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}