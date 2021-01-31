package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemBannerBinding

class BannerAdapter(
    private val listener: (Movie) -> Unit
) : BaseAdapter<Movie, ItemBannerBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemBannerBinding> =
        PosterHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    class PosterHolder(
        private val itemBannerBinding: ItemBannerBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemBannerBinding>(itemBannerBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemBannerBinding.movie = itemData
        }
    }
}
