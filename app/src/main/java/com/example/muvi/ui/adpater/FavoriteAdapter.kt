package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val listener: (Movie) -> Unit,
    private val onDeleteClick: (View, Movie) -> Unit
) : BaseAdapter<Movie, ItemFavoriteBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemFavoriteBinding> = FavoriteHolder(
        ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener,
        onDeleteClick
    )

    class FavoriteHolder(
        private val itemFavoriteBinding: ItemFavoriteBinding,
        listener: (Movie) -> Unit,
        private val onDeleteClick: (View, Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemFavoriteBinding>(itemFavoriteBinding, listener) {

        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemFavoriteBinding.movie = itemData
            itemFavoriteBinding.textDeleteFavorite.setOnClickListener {
                onDeleteClick(it,itemData)
            }
        }
    }
}
