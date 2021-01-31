package com.example.muvi.ui.home

import android.content.Context
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.data.model.MovieType
import com.example.muvi.databinding.FragmentHomeBinding
import com.example.muvi.ui.adpater.DiscoveryAdapter
import com.example.muvi.ui.adpater.PosterAdapter
import com.example.muvi.ui.adpater.BannerAdapter
import com.example.muvi.utils.make
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResource: Int = R.layout.fragment_home
    override val viewModel by sharedViewModel<HomeViewModel>()

    private var isConnection = false

    private val discoveryAdapter = DiscoveryAdapter(::onItemPosterClick)
    private val trendingAdapter = PosterAdapter(::onItemPosterClick)
    private val topRateAdapter = PosterAdapter(::onItemPosterClick)
    private val topPopularAdapter = PosterAdapter(::onItemPosterClick)
    private val bannerAdapter = BannerAdapter(::onItemPosterClick)

    override fun initData() {
        isConnection = NetworkUtil.isConnection(requireContext())
        if (!isConnection) {
            view?.make(getString(R.string.err_internet))
            return
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
            recyclerDiscovery.adapter = discoveryAdapter
            recyclerTrending.adapter = trendingAdapter
            recyclerTopRate.adapter = topRateAdapter
            recyclerPopular.adapter = topPopularAdapter
            recyclerBanner.adapter = bannerAdapter
            PagerSnapHelper().attachToRecyclerView(recyclerBanner)
            pageIndicatorBanner.attachToRecyclerView(recyclerBanner)
        }
        initListener()
    }

    private fun initListener(){
        binding.imageFavorite.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
            findNavController().navigate(action)
        }
        binding.imageDiscoveryAll.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MovieType.Discovery.toString())
            findNavController().navigate(action)
        }
        binding.imageTrendingAll.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MovieType.Trending.toString())
            findNavController().navigate(action)
        }

        binding.imageTopRateAll.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MovieType.TopRate.toString())
            findNavController().navigate(action)
        }

        binding.imagePopularAll.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MovieType.Popular.toString())
            findNavController().navigate(action)
        }
    }

    private fun onItemPosterClick(movie: Movie) {
        binding.apply {
            navigateToList(movie.id)
        }
    }

    private fun navigateToList(idMovie: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(idMovie)
        findNavController().navigate(action)
    }
}
