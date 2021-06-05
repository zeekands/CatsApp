package com.zeekands.core.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.zeekands.core.databinding.ItemRecyclerviewBinding
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.utils.DiffUtils
import java.util.*

class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    private var listData = ArrayList<Cat>()
    var onItemClick: ((Cat) -> Unit)? = null

    fun setData(newListData: List<Cat>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtils(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedData(swipedPosition: Int): Cat = listData[swipedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val itemRecyclerviewBinding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(itemRecyclerviewBinding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class CatViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(Cat: Cat) {
            with(binding) {

                title.text = Cat.name
                lifeSpan.text = "LifeSpan : " + Cat.lifeSpan
                origin.text = Cat.origin + " " + Cat.countryCode

                Glide.with(itemView.context)
                    .load(Cat.image)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            noImage.visibility = View.VISIBLE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            noImage.visibility = View.GONE
                            return false
                        }
                    })
                    .into(poster)

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}