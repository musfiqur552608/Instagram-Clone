package org.freedu.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.freedu.instagramclone.Models.Post
import org.freedu.instagramclone.databinding.MyPostListBinding

class MyPostAdapter(var context:Context, var postList:ArrayList<Post>):RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: MyPostListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyPostListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.postImg
        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.postImg)
    }
}