package com.example.muvi.ui.trailer

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.base.BottomNavigationListener
import com.example.muvi.databinding.FragmentTrailerMovieBinding
import com.example.muvi.ui.detail.DetailViewModel
import com.example.muvi.utils.hide
import com.example.muvi.utils.make
import com.example.muvi.utils.show
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrailerFragment : BaseFragment<FragmentTrailerMovieBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_trailer_movie

    override val viewModel by viewModel<DetailViewModel>()

    private val args: TrailerFragmentArgs by navArgs()

    private var bottomNavigationListener: BottomNavigationListener? = null

    private var youtube: YouTubePlayer? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) bottomNavigationListener = context
        requireActivity()?.let {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            it.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.idMovie)
    }

    override fun onPause() {
        super.onPause()
        youtube?.pause()
    }

    override fun onDestroy() {
        binding.youtubePlayerView.release()
        bottomNavigationListener?.showNav()
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity()?.let {
            it.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@TrailerFragment
            detailViewModel = viewModel
            toolbarPlayTrailer.hide()
        }
        bottomNavigationListener?.hideNav()
        initListener()
        videoObserver()
    }

    private fun initListener() {
        binding.toolbarPlayTrailer.setNavigationOnClickListener {
            bottomNavigationListener?.showNav()
            findNavController().popBackStack()
        }
    }


    private fun videoObserver() {
        viewModel.video.observe(viewLifecycleOwner, Observer {
            loadVideo(it.key)
        })
    }

    private fun loadVideo(youtubeId: String) {
        if(youtubeId.isNullOrEmpty()){
            view?.make("This movie don't have trailer ! ")
            return
        }
        val youtubeView = binding.youtubePlayerView
        youtubeView.enterFullScreen()
        youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
                youtube = youTubePlayer
            }
        })

        youtubeView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                requireActivity()?.let {
                    it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    it.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                }
                binding.toolbarPlayTrailer.hide()
                bottomNavigationListener?.hideNav()
            }

            override fun onYouTubePlayerExitFullScreen() {
                requireActivity()?.let {
                    it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    it.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                }
                binding.toolbarPlayTrailer.show()
                bottomNavigationListener?.hideNav()
            }
        })
    }
}
