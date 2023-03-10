package com.jmsc.kotlinflowdemo

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var mList: List<Item> = emptyList()
    var selectedPosition: Int = -1

    var onItemClick: ((Int,Item) -> Unit)? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
  
        return ViewHolder(view)
    }
  
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  
        val ItemsViewModel = mList[position]
  
        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)
  
        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position,mList[position])
        }
  
    }
  
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateItems(items: List<Item>?) {
        mList = items ?: emptyList()
        notifyDataSetChanged()

        selectedPosition = -1
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}