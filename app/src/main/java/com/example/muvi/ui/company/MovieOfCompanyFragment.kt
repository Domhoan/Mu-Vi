package com.example.muvi.ui.actor

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentMovieOfCompanyBinding
import com.example.muvi.ui.adpater.MoviesTypeAdapter
import com.example.muvi.ui.company.MovieOfCompanyViewModel
import kotlinx.android.synthetic.main.fragment_movie_of_company.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieOfCompanyFragment: BaseFragment<FragmentMovieOfCompanyBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_of_company

    override val viewModel by viewModel<MovieOfCompanyViewModel>()

    private val args: MovieOfCompanyFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getData(args.idCompany)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieOfCompanyFragment
            nameCompany = args.nameCompany
            viewModel.getData(args.idCompany)
            movieOfCompanyVM = viewModel
            recyclerMoviesOfCompany.adapter = moviesTypeAdapter
            recyclerMoviesOfCompany.scrollToPosition(0)
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieOfCompanyFragmentDirections.actionMovieOfCompanyFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
