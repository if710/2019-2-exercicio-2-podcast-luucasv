package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tryDownloadDataAndDisplayList("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml")
    }

    fun tryDownloadDataAndDisplayList(url: String) {
        doAsync {
            try {
                downloadXmlAndUpdateDatabase(url)
            } catch (e: Exception) {
                // ignore connection errors
            }

            val database = ItemFeedDatabase.getDatabase(this@MainActivity)
            val itemList = database.itemFeedDao().getAll()

            uiThread {
                displayAdapterItems(itemList)
            }
        }
    }

    fun downloadXmlAndUpdateDatabase(url: String) {
        val xml = URL(url).readText()
        val xmlItemList = Parser.parse(xml)
        val database = ItemFeedDatabase.getDatabase(this@MainActivity)
        database.itemFeedDao().insertAll(*xmlItemList.toTypedArray())
    }

    fun displayAdapterItems(itemFeedList: List<ItemFeed>) {
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = ItemFeedAdapter(itemFeedList)
    }
}
