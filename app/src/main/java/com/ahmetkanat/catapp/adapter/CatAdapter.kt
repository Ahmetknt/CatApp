package com.ahmetkanat.catapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ahmetkanat.catapp.R
import com.ahmetkanat.catapp.view.DetayActivity
import com.ahmetkanat.catapp.databinding.CardTasarimBinding
import com.ahmetkanat.catapp.model.Cat
import com.squareup.picasso.Picasso


class CatAdapter(private val context : Context,private val catList : List<Cat>) : RecyclerView.Adapter<CatAdapter.CatHolder>() {

    inner class CatHolder(val cardTasarimBinding: CardTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardTasarimBinding.inflate(layoutInflater,parent,false)

        return CatHolder(binding)

    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val cat = catList[position]

        val url = cat.image.url

        holder.cardTasarimBinding.apply {
            nameText.text = cat.name
            Picasso.get().load(url).into(imageView)
        }
        holder.cardTasarimBinding.cardView.setOnClickListener {
            val intent = Intent(context, DetayActivity::class.java)
            intent.putExtra("detay",cat)
            context.startActivity(intent)
        }

        //bu kontrol boolean ile de sağlanabilirdi fakat card yapısı olduğu için boolean yapısı olmadı favorilere ekleme çıkarmaya böyle bir çözüm buldum.
        var display = R.drawable.star_off

        holder.cardTasarimBinding.starButton.setOnClickListener {
            if(display == R.drawable.star_off){
                holder.cardTasarimBinding.starButton.setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.star
                ))
                display = R.drawable.star
            }
            else if(display == R.drawable.star){
                holder.cardTasarimBinding.starButton.setImageDrawable(
                    ContextCompat.getDrawable(context,R.drawable.star_off
                ))
                display = R.drawable.star_off
            }
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

}
