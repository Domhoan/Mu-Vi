package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemPosterBinding

class PosterAdapter(
    private val listener: (Movie) -> Unit
) : BaseAdapter<Movie, ItemPosterBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemPosterBinding> =
        PosterHolder(
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    class PosterHolder(
        private val itemPosterBinding: ItemPosterBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemPosterBinding>(itemPosterBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemPosterBinding.movie = itemData
        }
    }
}
