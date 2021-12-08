package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FlickrViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private var TAG:String="FlickrListAdapter"
    val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
    val description = view.findViewById<TextView>(R.id.description)
}
class FlickrListAdapter (var photoList: ArrayList<Photo>):RecyclerView.Adapter<FlickrViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_list, parent, false)
        return FlickrViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
        val photoItem = photoList[position]
        holder.description.text = photoItem.tags

        Picasso.get().load(photoItem.photoUrl).
        error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(holder.thumbnail)

    }
     fun getPhoto(position:Int):Photo{
        return photoList.get(position)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "Item count is" + photoList.size)
        return photoList.size
    }
    fun loadNewData(newPhotolist: ArrayList<Photo>){
        photoList= newPhotolist
        notifyDataSetChanged()
    }
}


