package br.ufpe.cin.android.podcast

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ItemFeed) {
        val context = itemView.context
        val titleView = itemView.findViewById<TextView>(R.id.item_title)
        val dateView = itemView.findViewById<TextView>(R.id.item_date)
        val downloadButtonView = itemView.findViewById<Button>(R.id.item_action)

        titleView.text = item.title
        titleView.setOnClickListener {
            val intent = Intent(context.applicationContext, EpisodeDetailActivity::class.java)
            intent.putExtra("item.downloadLink", item.downloadLink)
            context.startActivity(intent)
        }

        dateView.text = item.pubDate

        downloadButtonView.setOnClickListener {
            Toast.makeText(context, "Downloading episode...", Toast.LENGTH_SHORT).show()
        }
    }
}