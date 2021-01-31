package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemDiscoveryBinding

class DiscoveryAdapter(private val listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemDiscoveryBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemDiscoveryBinding> =
        DiscoveryHolder(
            ItemDiscoveryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )

    class DiscoveryHolder(
        private val itemDiscoveryBinding: ItemDiscoveryBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemDiscoveryBinding>(itemDiscoveryBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemDiscoveryBinding.discovery = itemData
        }
    }
}
