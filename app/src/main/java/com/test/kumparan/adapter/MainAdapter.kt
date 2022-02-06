package com.test.kumparan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.core.domain.model.PostModel
import com.test.core.utils.loadAvatar
import com.test.core.utils.getCapsSentences
import com.test.kumparan.R
import com.test.kumparan.databinding.ItemPostBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {

    private var listData = ArrayList<PostModel>()
    var onItemClick: ((PostModel, Int) -> Unit)? = null

    companion object {
        const val POST = 1
        const val USER = 2
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<PostModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPostBinding.bind(itemView)
        fun bind(data: PostModel) {
            with(binding) {
                loadAvatar(null, ivUser, data.name)
                tvTitle.text = getCapsSentences(data.title.toString())
                tvBody.text = data.body
                tvName.text = getCapsSentences(data.name.toString())
                tvCompany.text = getCapsSentences(data.company.toString())
            }
        }

        init {
            binding.clPostCard.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition], POST)
            }

            binding.clUserCard.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition], USER)
            }
        }
    }
}