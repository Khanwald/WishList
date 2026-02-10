package com.example.wishlist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var wishList: List<ListItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        wishList = ArrayList()
        val listView = findViewById<RecyclerView>(R.id.listRv)

        val adapter = ListAdapter(wishList)

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val index = adapter.itemCount
            val name = findViewById<EditText>(R.id.nameI)
            val price = findViewById<EditText>(R.id.priceI)
            val url = findViewById<EditText>(R.id.urlI)

            (wishList as MutableList<ListItem>).add(ListItem(name.text.toString(), price.text.toString(),url.text.toString()))
            adapter.notifyItemInserted(index)

            name.text.clear()
            price.text.clear()
            url.text.clear()
        }
        val mainView: View = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputContainer: View = findViewById(R.id.inputContainer)
        ViewCompat.setOnApplyWindowInsetsListener(inputContainer) { v, insets ->
            val imeInsets: Insets = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(
                v.paddingLeft,
                v.paddingTop,
                v.paddingRight,
                imeInsets.bottom
            )
            insets
        }


    }
}
