package org.freedu.instagramclone.Post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import org.freedu.instagramclone.databinding.ActivityPostsBinding
import org.freedu.instagramclone.utils.POST_FOLDER
import org.freedu.instagramclone.utils.USER_PROFILE_FOLDER
import org.freedu.instagramclone.utils.uploadImage

class PostsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                if (it != null) {
                    binding.selectImage.setImageURI(uri)
                }
            }
        }
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

        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }
    }
}