package com.ahmetkanat.catapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkanat.catapp.R
import com.ahmetkanat.catapp.databinding.CardTasarimBinding
import com.ahmetkanat.catapp.model.Cat
import com.squareup.picasso.Picasso
import java.util.*


class CatAdapter(private val context : Context,private val catList : List<Cat>,private val listener : Listener) : RecyclerView.Adapter<CatAdapter.CatHolder>() {

    interface Listener{
        fun onItemCardClick(cat : Cat)
    }

    inner class CatHolder(val cardTasarimBinding: CardTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root){
        fun bind(cat: Cat, listener: Listener){

            cardTasarimBinding.cardView.setOnClickListener {
                listener.onItemCardClick(cat)
            }
        }


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
        holder.bind(cat,listener)

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
