package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            submitBtn.setOnClickListener {
                if (!binding.firstNameEdtv.text.isNullOrEmpty() && !binding.lastNameEdtv.text.isNullOrEmpty() && isEmailValid(
                        binding.emailEdtv.text.toString()
                    )
                ) {
                    //API Call
                    errorText.visibility= View.GONE
                    Toast.makeText(this@MainActivity,"Done",Toast.LENGTH_SHORT).show()
                }else{
                    errorText.visibility= View.VISIBLE
                }
            }
            emailEdtv.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(email: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(email: Editable?) {
                    if (isEmailValid(email?.toString())){
                        emailEdtv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_24, 0)
                    }else{
                        emailEdtv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                }
            })
        }
    }
    fun isEmailValid(email: String?): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        return false
    }
}