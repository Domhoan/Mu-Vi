package com.example.muvi.ui.actor

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentMovieOfActorBinding
import com.example.muvi.ui.adpater.MoviesTypeAdapter
import com.example.muvi.ui.genres.MovieTypeFragmentArgs
import com.example.muvi.ui.genres.MovieTypeFragmentDirections
import com.example.muvi.utils.GenreUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieOfActorFragment: BaseFragment<FragmentMovieOfActorBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_of_actor

    override val viewModel by viewModel<MovieOfActorViewModel>()

    private val args: MovieOfActorFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {}

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieOfActorFragment
            nameActor = args.nameActor
            viewModel.getData(args.idActor)
            movieOfActorVM = viewModel
            recyclerMoviesOfActor.adapter = moviesTypeAdapter
            recyclerMoviesOfActor.scrollToPosition(0)
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieOfActorFragmentDirections.actionMovieOfActorFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
