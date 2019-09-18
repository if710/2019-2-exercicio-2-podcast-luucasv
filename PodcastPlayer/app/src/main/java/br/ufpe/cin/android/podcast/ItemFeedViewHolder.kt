package br.ufpe.cin.android.podcast

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ItemFeed) {
        itemView.findViewById<TextView>(R.id.item_title).text = item.title
        itemView.findViewById<TextView>(R.id.item_date).text = item.pubDate
        itemView.findViewById<Button>(R.id.item_action).setOnClickListener {
            Toast.makeText(itemView.context, "Downloading episode...", Toast.LENGTH_SHORT).show()
        }
    }
}