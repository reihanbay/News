package com.submission.news.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.bumptech.glide.Glide
import com.submission.news.R
import com.submission.news.databinding.FragmentDetailBinding
import com.submission.news.utils.helper.DateUtils

class DetailFragment : Fragment() {

    private var binding : FragmentDetailBinding? = null
    private val bind get() = binding!!
    private val dataArg : DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initAction()
    }
    private fun initUI() {
        bind.apply {
            Glide.with(requireContext()).load(dataArg.data.urlToImage).placeholder(R.drawable.ic_broken_image).into(ivNews)
            tvAuthor.text = getString(R.string.author, dataArg.data.author)
            tvTitle.text = dataArg.data.title
            tvContent.text = dataArg.data.content
            tvDate.text = DateUtils.formatDate(dataArg.data.publishedAt.toString(),"yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy")
        }
    }

    private fun initAction() {
        bind.actionShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(dataArg.data.url)
            requireActivity().startActivity(intent)
        }
        bind.btnBack.setOnClickListener { findNavController().popBackStack() }
    }


}