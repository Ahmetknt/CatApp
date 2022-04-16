package com.ahmetkanat.catapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmetkanat.catapp.databinding.ActivityDetayBinding
import com.ahmetkanat.catapp.model.Cat
import com.squareup.picasso.Picasso

class DetayActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cat = intent.getSerializableExtra("detay") as Cat

        Picasso.get().load(cat.image.url).into(binding.imageViewDetay)
        binding.descriptionText.text = cat.description
        binding.dogFriendlyText.text = cat.dog_friendly.toString()
        binding.originText.text = cat.origin
        binding.lifespanText.text = cat.life_span
        binding.wikipediaText.text = cat.wikipedia_url


    }
}