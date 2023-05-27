package com.example.searchlist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchlist.data.model.Record
import com.example.searchlist.databinding.ListItemBinding

class ProductAdapter(
    private val productList: List<Record>,
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ProductViewHolder(itemBinding, parent.context)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ProductViewHolder -> holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int =productList.size

    private inner class ProductViewHolder(val binding: ListItemBinding, val context: Context): BaseViewHolder<Record>(binding.root){
        override fun bind(item: Record) {
            Glide.with(context).load("https://ss632.liverpool.com.mx/sm/${item.productId}").centerCrop().into(binding.ivProduct)
            binding.tvName.text = item.productDisplayName
            binding.tvPrice.text = item.listPrice.toString()
            binding.tvDiscount.text = item.promoPrice.toString()
        }
    }
}

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}