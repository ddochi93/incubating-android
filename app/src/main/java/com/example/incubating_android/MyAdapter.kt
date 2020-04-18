package com.example.incubating_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class MyAdapter(private var imageList: ArrayList<Item>, private val longClicked: (Int) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(imageList[position].resourceId, imageList[position].title, longClicked)
    }

}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(resId: Int, title: String, longClicked: (Int) -> Unit) {
        itemView.item_img.setImageResource(resId)
        itemView.item_title.text = title

        itemView.setOnLongClickListener {
            longClicked(resId)
            return@setOnLongClickListener true
        }
    }
}