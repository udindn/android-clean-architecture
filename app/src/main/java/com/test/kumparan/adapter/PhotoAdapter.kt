package com.test.kumparan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.core.domain.model.PhotoModel
import com.test.core.utils.getCapsSentences
import com.test.core.utils.loadImage
import com.test.kumparan.R
import com.test.kumparan.databinding.ItemAlbumBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ListViewHolder>() {

    private var listData = ArrayList<PhotoModel>()
    var onItemClick: ((PhotoModel, ImageView) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<PhotoModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAlbumBinding.bind(itemView)
        fun bind(data: PhotoModel) {
            with(binding) {
                tvPhotoName.text = getCapsSentences(data.title)
                Picasso.get().load(data.url).noFade().into(ivPhoto)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition], binding.ivPhoto)
            }
        }
    }
}