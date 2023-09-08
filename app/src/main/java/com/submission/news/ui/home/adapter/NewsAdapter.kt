package com.submission.news.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.news.R
import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.databinding.ItemRvNewsBinding
import com.submission.news.utils.helper.DateUtils

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    val list: MutableList<NewsDataClass> = mutableListOf()
    var count = list.size
    var isLogin = false
    private lateinit var setOnClick: SetOnClickListener
    fun setOnClickListener(click: SetOnClickListener) {
        setOnClick = click
    }

    fun setSession(hasLogin: Boolean) {
        isLogin = hasLogin
        notifyItemChanged(14)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(items : List<NewsDataClass>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
    inner class NewsViewHolder(val bind : ItemRvNewsBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemRvNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = list[position]
        holder.bind.apply {
            Glide.with(holder.itemView.context).load(data.urlToImage).placeholder(R.drawable.ic_broken_image).into(ivNews)
            ivNews.clipToOutline = true
            tvTitle.text = data.title
            tvDate.text = DateUtils.formatDate(data.publishedAt.toString(),"yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy")
            containerRv.setOnClickListener {
                setOnClick.setOnClicked(data)
            }
            if (position == 14) tvInfo.isGone = isLogin
        }
    }

    override fun getItemCount(): Int = if (isLogin) list.size else if (list.size == 0) 0  else 15//Maksimalkan 15 Data saja

    interface SetOnClickListener {
        fun setOnClicked(data: NewsDataClass)
    }
}