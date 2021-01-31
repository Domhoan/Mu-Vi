package com.example.muvi.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muvi.base.BaseAdapter
import com.example.muvi.base.BaseViewHolder
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.ItemMovieBinding

class MoviesTypeAdapter(
    private val listener: (Movie) -> Unit,
    loadMore: () -> Unit
) : BaseAdapter<Movie, ItemMovieBinding>(listener, loadMore) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemMovieBinding> = MovieHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener
    )

    class MovieHolder(
        private val itemMovieBinding: ItemMovieBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemMovieBinding>(itemMovieBinding, listener) {

        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemMovieBinding.movie = itemData
        }
    }
}


