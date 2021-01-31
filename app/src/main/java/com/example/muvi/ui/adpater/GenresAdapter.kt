package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Genre
import com.example.muvi.databinding.ItemGenreBinding

class GenresAdapter(
    private val listener: (Genre) -> Unit
) : BaseAdapter<Genre, ItemGenreBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Genre, ItemGenreBinding> =
        GenresHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class GenresHolder(
        private val itemGenreBinding: ItemGenreBinding,
        listener: (Genre) -> Unit
    ) : BaseViewHolder<Genre, ItemGenreBinding>(itemGenreBinding, listener) {
        override fun onBind(itemData: Genre) {
            super.onBind(itemData)
            itemGenreBinding.genre = itemData
        }
    }
}
