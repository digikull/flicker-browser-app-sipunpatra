package com.example.myapplication
import android.view.View

interface OnRecyclerViewItemClick {
        fun onItemClick(view: View, poisition:Int)
        fun onItemLongClick(view: View, poisition:Int)

    }

