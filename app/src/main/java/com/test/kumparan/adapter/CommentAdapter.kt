package com.test.kumparan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.core.domain.model.CommentModel
import com.test.core.utils.getCapsSentences
import com.test.core.utils.loadAvatar
import com.test.kumparan.R
import com.test.kumparan.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ListViewHolder>() {

    private var listData = ArrayList<CommentModel>()
    var onItemClick: ((CommentModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<CommentModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCommentBinding.bind(itemView)
        fun bind(data: CommentModel) {
            with(binding) {
                loadAvatar(null, ivAvatarComment, data.name)
                tvUser.text = getCapsSentences(data.name.toString())
                tvComment.text = data.body
            }
        }

        init {
            binding.tvUser.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}