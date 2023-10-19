package com.example.moviefilterproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MoviesAdapter (val dataList:List<Index>): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){
    class MovieViewHolder(viewHolder: View):RecyclerView.ViewHolder(viewHolder){
        val movieImage = viewHolder.findViewById<ImageView>(R.id.movie_image)
        val movieTitle = viewHolder.findViewById<TextView>(R.id.text_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.design_layout, parent, false)
        return MovieViewHolder(rowView)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = dataList[position]
        Glide.with(holder.itemView).load("http://d2xkd1fof6iiv9.cloudfront.net/images/courses/${data.id}/169_820.jpg").into(holder.movieImage)
        holder.movieTitle.text = data.title
    }

}
