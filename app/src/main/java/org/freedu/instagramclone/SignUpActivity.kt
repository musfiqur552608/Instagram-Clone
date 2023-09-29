package org.freedu.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.freedu.instagramclone.Models.User
import org.freedu.instagramclone.databinding.ActivitySignUpBinding
import org.freedu.instagramclone.utils.USER_NODE
import org.freedu.instagramclone.utils.USER_PROFILE_FOLDER
import org.freedu.instagramclone.utils.uploadImage

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER){
                if(it==null){

                }else{
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        binding.signupBtn.setOnClickListener {
            if((binding.nameEtxt.editText?.text.toString() == "") or (binding.emailEtxt.editText?.text.toString() == "") or (binding.passEtxt.editText?.text.toString() == "")){
                Toast.makeText(this@SignUpActivity, "Please fill all the information", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailEtxt.editText?.text.toString(),
                    binding.passEtxt.editText?.text.toString()
                ).addOnCompleteListener {
                    result ->
                    if(result.isSuccessful){
                        user.name = binding.nameEtxt.editText?.text.toString()
                        user.email = binding.emailEtxt.editText?.text.toString()
                        user.password = binding.passEtxt.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                                finish()
                            }
                    }else{
                        Toast.makeText(this@SignUpActivity,result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

    }
}