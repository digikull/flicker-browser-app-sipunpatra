package com.example.myapplication

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class PhotoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
       // intent.extras!!.getString("PHOTO_TITLE")
        val photo=intent.getParcelableExtra<Photo>("PHOTO")as Photo

        val tvtitle=findViewById<TextView>(R.id.tvtitle)
        val tvauthor=findViewById<TextView>(R.id.tvauthor)
        val tvtags=findViewById<TextView>(R.id.tvtags)
        val imageView=findViewById<ImageView>(R.id.imageView)

        tvtitle.text=photo.title
        tvauthor.text=photo.author
        tvtags.text=photo.tags

        Picasso.get().load(photo.photoUrl).error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(imageView)
    }
}