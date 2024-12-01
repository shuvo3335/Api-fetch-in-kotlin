package com.example.kotlinapplication

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdapter (val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<DataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_view,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        val player = MediaPlayer.create(context,data.preview.toUri())

        holder.albumName.text = data.title
        Picasso.get().load(data.album.cover).into(holder.albumImage)
        holder.playBtn.setOnClickListener {
            player.start()
        }
        holder.pauseBtn.setOnClickListener {
            player.pause()
        }
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        /*init {
            val albumName = itemView.findViewById<TextView>(R.id.textView)
            val albumImage = itemView.findViewById<ImageView>(R.id.imageView)
            val playBtn = itemView.findViewById<ImageButton>(R.id.imageButtonPlay)
            val pauseBtn = itemView.findViewById<ImageButton>(R.id.imageButtonPause)
        }*/
        val albumName = itemView.findViewById<TextView>(R.id.textView)
        val albumImage = itemView.findViewById<ImageView>(R.id.imageView)
        val playBtn = itemView.findViewById<ImageButton>(R.id.imageButtonPlay)
        val pauseBtn = itemView.findViewById<ImageButton>(R.id.imageButtonPause)

    }
}