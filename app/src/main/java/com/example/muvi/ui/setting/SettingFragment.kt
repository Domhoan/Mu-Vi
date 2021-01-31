package com.example.muvi.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import com.example.muvi.R
import com.example.muvi.base.BaseFragment
import com.example.muvi.databinding.FragmentSettingBinding
import com.example.muvi.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_setting

    override val viewModel by sharedViewModel<FavoriteViewModel>()

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@SettingFragment
        }
        initListener()
    }

    private fun initListener() {
        binding.textAboutApp.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.text_dialog_about_app))
            builder.setMessage(getString(R.string.text_about_app_by_ht))
            //Nút Cancel
            builder.setNegativeButton(getString(R.string.text_ok)) {
                    dialog,
                    _ -> dialog.cancel()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
        binding.textFeedback.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse(getString(R.string.uri_mail_to))
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_feedback_subject))
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            }
        }
        binding.textShareFriend.setOnClickListener {
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.url_facebook))
                putExtra(Intent.EXTRA_TITLE, getString(R.string.message_about_app))
                data = Uri.parse(getString(R.string.uri_about_app))
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }, null)
            startActivity(share)
        }
    }
}
