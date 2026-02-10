package com.example.wishlist
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.wishlist.ListItem
import com.example.wishlist.R
import androidx.core.net.toUri

class ListAdapter(private val wishList: List<ListItem>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val context = parent.context
        val inflator = LayoutInflater.from(context)

        val contactView = inflator.inflate(R.layout.list_item, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = wishList.get(position)

        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price
        holder.urlTextView.text = item.url


    }



    override fun getItemCount(): Int {
        return wishList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val nameTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView


        init {

             nameTextView = itemView.findViewById(R.id.name)
             priceTextView = itemView.findViewById(R.id.price)
             urlTextView = itemView.findViewById(R.id.url)

            itemView.setOnClickListener {
                try {
                    val browserIntent = Intent(Intent.ACTION_VIEW,
                        urlTextView.text.toString().toUri())
                    ContextCompat.startActivity(it.context, browserIntent, null)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(it.context, "Invalid URL for " + nameTextView.text.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}