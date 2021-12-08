package com.example.myapplication
import android.os.AsyncTask
import android.util.Log
import android.view.View
import org.json.JSONArray
import org.json.JSONObject

class GetFlickrJsonData(val listener: OnDataAvailable):AsyncTask<String,Void,ArrayList<Photo>>() {
 private var TAG: String = "GetFlickrJsonData"
 override fun doInBackground(vararg p0: String?):ArrayList<Photo> {
  var photoList: ArrayList<Photo> = ArrayList()
  try {
   var jsonString: String = p0[0]!!
   var jsonObject: JSONObject = JSONObject(jsonString)
   var jsonArray: JSONArray = jsonObject.getJSONArray("items")
   var i: Int = 0
   while (i < jsonArray.length()) {

    var photo: Photo = Photo()
    var photoObject: JSONObject = jsonArray.getJSONObject(i)
    var title = photoObject.getString("title")
    var mediaObj = photoObject.getJSONObject("media")
    var photoUrl=mediaObj.getString("m")
    var dateTaken = photoObject.getString("date_taken")
    var description = photoObject.getString("description")
    var author = photoObject.getString("author")
    var tags = photoObject.getString("tags")

    photo.title = title
    photo.photoUrl = photoUrl
    photo.dateTaken = dateTaken
    photo.description = description
    photo.author = author
    photo.tags = tags

    photoList.add(photo)
    i++
   }

  } catch (e: Exception) {
   Log.d(TAG,e.message.toString())

  }
  return photoList
 }

 override fun onPostExecute(photoList: ArrayList<Photo>) {
  Log.d(TAG, "list is" + photoList.toString())
  listener.OnDataAvailable(photoList)
 }
}