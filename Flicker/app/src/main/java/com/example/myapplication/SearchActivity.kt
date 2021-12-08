package com.example.myapplication

import  android.app.SearchManager
import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.example.myapplication.R

class SearchActivity : AppCompatActivity() {
    private var TAG:String="SearchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        var toolbar = findViewById<androidx.appcompat.widget.Toolbar>(
            R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG,"OnCreateOption menu start")
        menuInflater.inflate(R.menu.menu_search,menu)
        Log.d(TAG,"OnCreateOptionMenu ends")
//..........................................................................
        val searchManager = getSystemService(Context.SEARCH_SERVICE)as SearchManager

        val searchView= menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        Log.d(TAG,"component name $componentName")
        val searchableInfo= searchManager.getSearchableInfo(componentName)
        searchView.setSearchableInfo(searchableInfo)
        Log.d(TAG,"hint is"+searchView.queryHint)
        searchView.isIconified=false
//...................................................................................

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sharedPreferences=PreferenceManager.getDefaultSharedPreferences(applicationContext)
                sharedPreferences.edit().putString("FLICKR QUARY",query).apply()
                searchView.clearFocus()
                finish()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
              return false
            }

        })

        return true
    }
}