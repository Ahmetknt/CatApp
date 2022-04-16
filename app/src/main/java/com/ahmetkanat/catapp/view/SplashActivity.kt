package com.ahmetkanat.catapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmetkanat.catapp.R
import com.ahmetkanat.catapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            imageView3.alpha = 0f
            imageView3.animate().setDuration(1500).alpha(1f).withEndAction{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}