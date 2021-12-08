package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.OnRecyclerViewItemClick

class RecyclerViewItemClickListener (context: Context,recyclerView: RecyclerView,listener: OnRecyclerViewItemClick):RecyclerView.SimpleOnItemTouchListener(){
    private var TAG:String="RecyclerViewItemClickListener"

    val gestureDectector=GestureDetectorCompat(context,object :GestureDetector.SimpleOnGestureListener(){
        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG,"OnLongPress called")
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            val position:Int=recyclerView.getChildAdapterPosition(childView!!)
            listener.onItemLongClick(childView,position)

           // super.onLongPress(e)
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG,"OnSingleTap called")
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            val position:Int=recyclerView.getChildAdapterPosition(childView!!)
            listener.onItemClick(childView,position)
            return true
            //return super.onSingleTapUp(e)
        }
    })
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG,"OnIntercept $e")
        val result=gestureDectector.onTouchEvent(e)
        return result
       // return super.onInterceptTouchEvent(rv, e)
    }
}