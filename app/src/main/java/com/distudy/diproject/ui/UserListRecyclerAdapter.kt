package com.distudy.diproject.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.distudy.diproject.data.UserProfileInfo
import com.distudy.diproject.databinding.ItemUserSimpleListBinding

class UserListRecyclerAdapter(private val context: Context, private val userList: List<UserProfileInfo>?) :
    RecyclerView.Adapter<UserListRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemUserSimpleListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userInfo: UserProfileInfo?) {
            Glide.with(context).load(userInfo?.avatar_url).override(100).into(binding.userImage)
            binding.userName.text = userInfo?.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG,"onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemUserSimpleListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList?.get(position))
    }

    companion object{
        private const val TAG = "UserListRecyclerAdapter"
    }
}