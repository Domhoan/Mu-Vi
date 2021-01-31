package com.example.muvi.ui.seeall

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentSeeAllBinding
import com.example.muvi.ui.adpater.MoviesTypeAdapter
import com.example.muvi.utils.GenreUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_see_all
    override val viewModel by viewModel<SeeAllViewModel>()
    private val args: SeeAllFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getData(args.typeMovie)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@SeeAllFragment
            nameTitle = args.typeMovie
            viewModel.getData(args.typeMovie)
            seeAllVM = viewModel
            recyclerSeeAll.adapter = moviesTypeAdapter
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = SeeAllFragmentDirections.actionSeeAllFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
