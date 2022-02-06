package com.test.kumparan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.core.domain.model.AlbumModel
import com.test.core.utils.getCapsSentences
import com.test.kumparan.R
import com.test.kumparan.databinding.ItemAlbumNameBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ListViewHolder>() {

    private var listData = ArrayList<AlbumModel>()
    var onItemClick: ((AlbumModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<AlbumModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_name, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAlbumNameBinding.bind(itemView)
        fun bind(data: AlbumModel) {
            with(binding) {
                tvAlbumName.text = getCapsSentences(data.title)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}