package com.naulian.article.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naulian.anhance.onClick
import com.naulian.anhance.setCardBgHex
import com.naulian.article.databinding.ViewCourseBinding
import com.naulian.article.models.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ItemViewHolder>(ItemDiffCallBack()) {
    private var listener: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCourseBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(private val binding: ViewCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context: Context = binding.root.context
        private val itemAdapter = TagAdapter()
        private val loManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        fun bind(position: Int) {
            val item = getItem(position)

            binding.apply {
                root.setCardBgHex("#ffffff")
                imageCourse.isVisible = false
                //imageCourse.loadWithGlide(item.image)
                textTitle.text = item.title
                textBody.text = item.body

                recyclerTags.layoutManager = loManager
                recyclerTags.adapter = itemAdapter
                itemAdapter.submitList(item.tags)

                root.onClick { listener?.invoke(item) }
            }
        }
    }

    fun onClick(actionOnClick: (Category) -> Unit) {
        listener = actionOnClick
    }

    class ItemDiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem.courseId == newItem.courseId
    }
}