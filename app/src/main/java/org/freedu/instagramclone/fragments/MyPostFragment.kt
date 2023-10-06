package org.freedu.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.freedu.instagramclone.Models.Post
import org.freedu.instagramclone.R
import org.freedu.instagramclone.adapter.MyPostAdapter
import org.freedu.instagramclone.databinding.FragmentMyPostBinding

class MyPostFragment : Fragment() {
    private lateinit var binding:FragmentMyPostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMyPostBinding.inflate(inflater, container, false)
        var postList = ArrayList<Post>()
        var adapter = MyPostAdapter(requireContext(), postList)
        binding.postRecycler.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.postRecycler.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val tempList= arrayListOf<Post>()
            for(i in it.documents){
                var post:Post= i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}