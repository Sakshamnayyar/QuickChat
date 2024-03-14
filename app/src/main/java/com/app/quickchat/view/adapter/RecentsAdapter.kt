package com.app.quickchat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.quickchat.R
import com.app.quickchat.databinding.RecentItemViewBinding
import com.app.quickchat.model.data.User

class RecentsAdapter : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>() {

    private var mRecentsList: List<User>? = null

    inner class RecentsViewHolder(var mRecentsItemBinding: RecentItemViewBinding):
    RecyclerView.ViewHolder(mRecentsItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentsAdapter.RecentsViewHolder {
        val mRecentListItemBinding = DataBindingUtil.inflate<RecentItemViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recent_item_view,parent, false
        )

        return RecentsViewHolder(mRecentListItemBinding)
    }

    override fun getItemCount(): Int {
        return mRecentsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecentsAdapter.RecentsViewHolder, position: Int) {
        val currentUser = mRecentsList!![position]

        holder.mRecentsItemBinding.countryCode.text = currentUser.countryCode
        holder.mRecentsItemBinding.phone.text = currentUser.phone.toString()

    }

    fun setRecentList(mList: List<User>) {
        mRecentsList = mList
        notifyDataSetChanged()
    }
}