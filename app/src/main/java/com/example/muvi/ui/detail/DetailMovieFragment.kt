package com.example.muvi.ui.detail

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Actor
import com.example.muvi.data.model.Company
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentDetailMovieBinding
import com.example.muvi.ui.adpater.ActorAdapter
import com.example.muvi.ui.adpater.CompanyAdapter
import com.example.muvi.ui.adpater.MoviesTypeAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding>() {

    override val layoutResource: Int = R.layout.fragment_detail_movie
    override val viewModel by sharedViewModel<DetailViewModel>()
    private val recommendAdapter = MoviesTypeAdapter(::onItemRecommendClick) {}
    private val args: DetailMovieFragmentArgs by navArgs()
    private val companyAdapter = CompanyAdapter(::onCompanyClick)

    private val actorAdapter = ActorAdapter(::onActorClick)

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel.loadDetail(args.idMovie)
            detailVM = viewModel
            recyclerMoreMovie.adapter = recommendAdapter
            recyclerCompanies.adapter = companyAdapter
            recyclerActors.adapter = actorAdapter
        }
        initCollapsingToolbar()
        initListener()
    }

    private fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonPlayTrailer.setOnClickListener {
            if(viewModel.video.value != null){
                val action =
                    DetailMovieFragmentDirections.actionDetailFragmentToTrailerFragment(viewModel.detail.value?.id ?: -1)
                findNavController().navigate(action)
            }else{
                showToast("The movie Don't have trailer")
            }
        }
        binding.buttonAddMyList.setOnClickListener {
            viewModel.updateFavorite()
        }
    }

    private fun onItemRecommendClick(itemMovie: Movie) {
        viewModel.loadDetail(itemMovie.id)
    }

    private fun onActorClick(actor: Actor) {
        val action = DetailMovieFragmentDirections.actionDetailFragmentToMovieOfActorFragment(actor.name,actor.castId)
        findNavController().navigate(action)
    }

    private fun onCompanyClick(company: Company) {
        val action = DetailMovieFragmentDirections.actionDetailFragmentToMovieOfCompanyFragment(company.name,company.id)
        findNavController().navigate(action)
    }

    private fun initCollapsingToolbar() {
        var isShow = true
        var scrollRange = -1

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                toolbarDetail.setBackgroundResource(R.color.color_black)
                isShow = true
            } else if (isShow) {
                toolbarDetail.setBackgroundResource(android.R.color.transparent)
                isShow = false
            }
        })
    }
}
