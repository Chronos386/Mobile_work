package com.example.lab_2_anime.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_2_anime.DataSource.ListItem
import com.example.lab_2_anime.interf_components.GoToAnime3
import com.example.lab_2_anime.R
import com.example.lab_2_anime.interf_components.isOnline
import com.squareup.picasso.Picasso


class SpisAdapter3(private val context: Context, private val list: ArrayList<ListItem>,
                   private val MyOnClick: GoToAnime3
) :
    RecyclerView.Adapter<SpisAdapter3.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spisTitle: TextView = view.findViewById<View>(R.id.row_title) as TextView
        val spisDescr: TextView = view.findViewById<View>(R.id.row_descr) as TextView
        val spisInform: TextView = view.findViewById<View>(R.id.title_inform) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.spis_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        if(data.nameRu != null) {
            holder.spisTitle.text = data.nameRu
        }
        else
            holder.spisTitle.text = data.nameOriginal
        holder.spisDescr.text = "Страна: " + data.countries[0].country
        val str: String = data.year.toString() + " | " +
                data.ratingKinopoisk.toString() + "/10"
        holder.spisInform.text = str
        if(isOnline(context)){
            Picasso.get().load(data.posterURL).fit().placeholder(R.mipmap.ic_launcher).
            into(holder.itemView.findViewById<ImageView>(R.id.image))
        }
        holder.itemView.setOnClickListener {
            MyOnClick.onClicked(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}