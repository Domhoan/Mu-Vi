package com.example.muvi.ui.genres

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentMovieTypeBinding
import com.example.muvi.ui.adpater.MoviesTypeAdapter
import com.example.muvi.utils.GenreUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieTypeFragment : BaseFragment<FragmentMovieTypeBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_type

    override val viewModel by sharedViewModel<GenresViewModel>()

    private val args: MovieTypeFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getMovies(GenreUtil.getIdGenre(args.title))
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieTypeFragment
            titleFragment = args.title
            recyclerViewMoviesGenre.adapter = moviesTypeAdapter
            recyclerViewMoviesGenre.scrollToPosition(0)
            genreVM = viewModel
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieTypeFragmentDirections.actionMovieTypeFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
