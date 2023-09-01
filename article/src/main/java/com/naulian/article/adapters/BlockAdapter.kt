package com.naulian.article.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naulian.anhance.copyString
import com.naulian.anhance.loadWithGlide
import com.naulian.anhance.onClick
import com.naulian.anhance.onLongClick
import com.naulian.anhance.textTrim
import com.naulian.article.databinding.ViewBlockBinding
import com.naulian.article.models.Block
import com.naulian.glow.CodeTheme
import com.naulian.glow.Glow

class BlockAdapter : ListAdapter<Block, BlockAdapter.ItemViewHolder>(ItemDiffCallBack()) {
    private var listener: (Block.() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewBlockBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(private val binding: ViewBlockBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val context: Context = binding.root.context

        fun bind(position: Int) {
            val item = getItem(position)
            val imageUri = item.imageUri(context)
            val colored = Glow.highlight(item.code, item.language, CodeTheme.kotlinLight)

            binding.apply {
                //setVisibility
                textHeader.isVisible = item.header.isNotEmpty()
                textTitle.isVisible = item.title.isNotEmpty()
                textLabel.isVisible = item.label.isNotEmpty()

                cardImage.isVisible = item.image.isNotEmpty() || item.drawable != 0
                textDescription.isVisible = item.description.isNotEmpty()

                cardCode.isVisible = item.code.isNotEmpty()
                cardQuote.isVisible = item.quote.isNotEmpty()

                textBody.isVisible = item.body.isNotEmpty()
                textNote.isVisible = item.note.isNotEmpty()

                fieldEdit.isVisible = item.hint.isNotEmpty()
                buttonAction.isVisible = item.button.isNotEmpty()

                //setValue
                textHeader.text = item.header
                textTitle.text = item.title
                textLabel.text = item.label

                imageView.loadWithGlide(imageUri)
                textDescription.text = item.description

                textCode.text = colored.spanned
                textLanguage.text = item.language
                buttonCopy.onClick { context.copyString(item.code) }

                textQuote.text = item.quoteText
                cardQuote.onLongClick { context.copyString(item.quoteText) }

                textBody.text = item.body
                textNote.text = item.note

                fieldEdit.helperText = item.helper
                editText.hint = item.hint

                buttonAction.text = item.button
                buttonAction.onClick {
                    val input = editText.textTrim()
                    val block = item.copy(input = input)
                    listener?.invoke(block)
                }
            }
        }
    }

    fun onClick(actionOnClick: Block.() -> Unit) {
        listener = actionOnClick
    }

    class ItemDiffCallBack : DiffUtil.ItemCallback<Block>() {
        override fun areItemsTheSame(oldItem: Block, newItem: Block) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Block, newItem: Block) =
            oldItem.index == newItem.index
    }
}