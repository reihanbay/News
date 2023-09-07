package com.submission.news.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.databinding.FragmentHomeBinding
import com.submission.news.ui.home.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.submission.news.utils.api.Result

class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private val bind get() = binding!!
    private val viewModel : NewsViewModel by viewModel()
    private val rvAdapter : NewsAdapter by lazy { NewsAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()

        initObserver()
        setRv()
        initAction()

    }

    private fun initAction() {
        bind.refresh.setOnRefreshListener {
            viewModel.getNews()
            bind.refresh.isRefreshing = false
        }

        rvAdapter.setOnClickListener(object : NewsAdapter.SetOnClickListener{
            override fun setOnClicked(data: NewsDataClass) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data))
            }
        })

        bind.aboutPage.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }

    private fun setRv() {
        bind.rvNews.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun initObserver() {
        viewModel.getNews.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Loading -> {
                    bind.progressHorizontal.isVisible = true
                    bind.rvNews.isGone = true
                }
                is Result.Default -> {
                    bind.progressHorizontal.isGone = true
                    bind.ivEmpty.isVisible = false

                }
                is Result.Empty -> {
                    bind.ivEmpty.isVisible = true
                }
                is Result.Success -> {
                    bind.ivEmpty.isVisible = false
                    bind.rvNews.isVisible= true
                    bind.progressHorizontal.isGone = true
                    rvAdapter.setData(it.data.articles)
                }
                is Result.Failure -> {
                    Snackbar.make(bind.root, it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}