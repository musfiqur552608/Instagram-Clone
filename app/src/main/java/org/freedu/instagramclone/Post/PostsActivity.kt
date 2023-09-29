package org.freedu.instagramclone.Post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.freedu.instagramclone.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}