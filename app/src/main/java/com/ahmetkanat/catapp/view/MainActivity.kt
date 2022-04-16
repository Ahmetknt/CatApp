package com.ahmetkanat.catapp.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkanat.catapp.R
import com.ahmetkanat.catapp.adapter.CatAdapter
import com.ahmetkanat.catapp.databinding.ActivityMainBinding


import com.ahmetkanat.catapp.model.Cat
import com.ahmetkanat.catapp.network.APIUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList
//MutableLiveData<List<Cat>>()
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : CatAdapter
    private val apiUtils = APIUtils()
    private val compositeDisposable = CompositeDisposable()

    var catList = ArrayList<Cat>()
    var displayList = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCat()

        binding.imageButton.setOnClickListener {
            val intent = Intent(this,FavoriteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)
        val menuItem = menu!!.findItem(R.id.search_action)

        if(menuItem != null){
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        catList.forEach {
                            if(it.name.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        binding.rv.adapter!!.notifyDataSetChanged()
                    }else{
                        displayList.clear()
                        displayList.addAll(catList)
                        binding.rv.adapter!!.notifyDataSetChanged()
                    }


                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }



    private fun getCat(){

        compositeDisposable.add(
            apiUtils.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cat>>(){
                    override fun onSuccess(t: List<Cat>) {
                        catList = t as ArrayList<Cat>
                        displayList.addAll(catList)
                        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CatAdapter(this@MainActivity,displayList)
                        binding.rv.adapter=adapter
                        binding.rv.setHasFixedSize(true)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }



}