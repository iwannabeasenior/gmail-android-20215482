package com.example.currency_calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currency_calculator.databinding.ItemGmailBinding

class GmailAdapter : ListAdapter<Item, GmailAdapter.GmailVH>(ItemDiffCallBack()) {
    inner class GmailVH(private val binding: ItemGmailBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.firstText.text = getItem(position).firstText
            binding.name.text = getItem(position).name
            binding.firstContent.text = getItem(position).firstContent
            binding.secondContent.text = getItem(position).secondContent
            binding.time.text = getItem(position).time
            binding.color.setBackgroundColor(getItem(position).tint)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GmailVH {
        return GmailVH(ItemGmailBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: GmailVH, position: Int) {
        holder.bind(position)
    }

}
class ItemDiffCallBack: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}