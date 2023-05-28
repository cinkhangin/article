package com.naulian.article.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naulian.anhance.setCardBgHex
import com.naulian.article.databinding.ViewTagBinding
import com.naulian.article.models.Tag

class TagAdapter : ListAdapter<Tag, TagAdapter.ItemViewHolder>(ItemDiffCallBack()) {
    private var listener: ((Tag) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewTagBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(private val binding: ViewTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val context: Context = binding.root.context

        fun bind(position: Int) {
            val item = getItem(position)

            binding.apply {
                textName.text = item.text
                root.setCardBgHex(item.color)
            }
        }
    }

    fun onClick(actionOnClick: (Tag) -> Unit) {
        listener = actionOnClick
    }

    class ItemDiffCallBack : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag) =
            oldItem.text == newItem.text
    }
}