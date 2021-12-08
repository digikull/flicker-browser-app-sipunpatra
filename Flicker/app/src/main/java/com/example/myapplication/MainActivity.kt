package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),OnDataDownload ,OnDataAvailable,OnRecyclerViewItemClick {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var TAG: String = "MainActivity"
    private var adapter: FlickrListAdapter = FlickrListAdapter(ArrayList())
    private var baseUrl: String = "https://www.flickr.com/services/feeds/photos_public.gne"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        var url = createURIbuilder(baseUrl, "Truck", "en-us", true)

        var getRawData: GetRawData = GetRawData(this)

        // getRawData.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=srk&tagmode=any&format=json&nojsoncallback=1")
        getRawData.execute(url)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnItemTouchListener(
            RecyclerViewItemClickListener(applicationContext, recyclerView, this)
        )
        recyclerView.adapter = adapter
        /*
        var fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

 */
    }

    fun createURIbuilder(
        baseUrl: String,
        searchCriteria: String,
        lang: String,
        matchAll: Boolean
    ): String {
        var tagmodeString = "All"
        if (matchAll) tagmodeString = "Any"
        var finalUrlString: String = Uri.parse(baseUrl).buildUpon()
            .appendQueryParameter("tags", searchCriteria)
            .appendQueryParameter("tagmode", tagmodeString)
            .appendQueryParameter("lang", lang)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .build().toString()

        return finalUrlString
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDataDownloaded(result: String, downloadStatus: DownloadStatus) {
        Log.d(TAG, result)
        var getFlickrJsonData: GetFlickrJsonData = GetFlickrJsonData(this)
        getFlickrJsonData.execute(result)
    }

    override fun OnDataAvailable(data: ArrayList<Photo>) {
        Log.d(TAG, data.toString())
        adapter.loadNewData(data)
    }

    override fun onItemClick(view: View, poisition: Int) {
        Log.d(TAG, "OnItemClick called")
        val toast = Toast.makeText(applicationContext, "item short click", Toast.LENGTH_SHORT)
        toast.show()

    }

    override fun onItemLongClick(view: View, position: Int) {
        val photoItem = adapter.getPhoto(position)
        val intent: Intent = Intent(this, PhotoDetailsActivity::class.java)
        intent.putExtra("PHOTO", photoItem)

        startActivity(intent)
    }

    override fun onResume() {
        Log.d(TAG, "OnResume entered")
        super.onResume()

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        var qresult = sharedPref.getString("FLICKR QUARY", "")

        if (qresult!!.isNotEmpty()) {
            Log.d(TAG, qresult)
            var url = createURIbuilder(baseUrl, qresult, "en-us", true)
            var getRawData: GetRawData = GetRawData(this)

            getRawData.execute(url)


        }
    }

}