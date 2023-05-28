package com.naulian.article.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naulian.anhance.onClick
import com.naulian.article.databinding.ViewChapterBinding
import com.naulian.article.models.Article

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ItemViewHolder>(ItemDiffCallBack()) {
    private var listener: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewChapterBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(private val binding: ViewChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context: Context = binding.root.context

        fun bind(position: Int) {
            val item = getItem(position)

            binding.apply {
                textTitle.text = item.title
                textBody.text = item.chapterId

                root.onClick { listener?.invoke(item) }
            }
        }
    }

    fun onClick(actionOnClick: (Article) -> Unit) {
        listener = actionOnClick
    }

    class ItemDiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem.chapterId == newItem.chapterId
    }
}