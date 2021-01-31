package com.example.muvi.ui.upcoming

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.data.model.Movie
import com.example.muvi.databinding.FragmentUpcomingBinding
import com.example.muvi.ui.adpater.UpComingAdapter
import com.example.muvi.utils.make
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpComingFragment : BaseFragment<FragmentUpcomingBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_upcoming

    override val viewModel by viewModel<UpComingViewModel>()

    private var isConnection = false

    private val upComingAdapter = UpComingAdapter(
        ::onDetailClick,
        ::onPlayClick
    ){
        viewModel.getUpComingMovie()
    }

    override fun initData() {
        isConnection = NetworkUtil.isConnection(requireContext())
        if (!isConnection) {
            view?.make(getString(R.string.err_internet))
            return
        }
        binding.lifecycleOwner = this
        binding.upComingViewModel = viewModel
        binding.recyclerUpComing.adapter = upComingAdapter
    }

    private fun onPlayClick(view: View, movie: Movie) {
        if(view.id == R.id.imagePlayYoutube){
            if (movie.video.isNullOrEmpty()){
                showToast(getString(R.string.err_no_trailer))
            } else{
                val action = UpComingFragmentDirections.actionUpComingFragmentToTrailerFragment(movie.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun onDetailClick(view: View, movie: Movie) {
        if (view.id == R.id.imageViewDetail){
            val action = UpComingFragmentDirections.actionUpComingFragmentToDetailFragment(movie.id)
            findNavController().navigate(action)
        }
    }
}
