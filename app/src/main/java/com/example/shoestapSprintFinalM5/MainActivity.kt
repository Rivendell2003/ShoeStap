package com.example.shoestapSprintFinalM5

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestapSprintFinalM5.adapter.ShoeAdapter
import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository
import com.example.shoestapSprintFinalM5.view.CartActivity

class MainActivity : AppCompatActivity() {

    private lateinit var shoeAdapter: ShoeAdapter
    private lateinit var cartCountTextView: TextView
    private lateinit var searchView: SearchView
    private lateinit var originalShoeList: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupRecyclerView()
        updateCartCount()
    }

    private fun initializeViews() {
        cartCountTextView = findViewById(R.id.cart_count_text_view)
        searchView = findViewById(R.id.search_view)

        findViewById<Button>(R.id.view_cart_button).setOnClickListener {
            openCartActivity()
        }

        setupSearchView()
    }

    private fun openCartActivity() {
        val intent = Intent(this@MainActivity, CartActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.shoe_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        originalShoeList = ShoeRepository.returnShoeList() // save a la lista original
        shoeAdapter = ShoeAdapter(
            originalShoeList,
            { item -> removeItem(item) },
            { item -> addItemToCart(item) }
        )
        recyclerView.adapter = shoeAdapter
    }

    private fun addItemToCart(item: Item) {
        ShoeRepository.addToCart(item)
        updateCartCount()
        Toast.makeText(this, "${item.name} agregado al carrito", Toast.LENGTH_SHORT).show()
    }

    private fun updateCartCount() {
        val itemCount = ShoeRepository.returnCartItems().size
        cartCountTextView.text = itemCount.toString()
    }

    private fun removeItem(item: Item) {
        ShoeRepository.removeShoe(item)
        shoeAdapter.notifyDataSetChanged() // update el adapter despuees de eliminar
        updateCartCount()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterShoeList(newText)

                //  el color del texto del EditText interno
                val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
                searchEditText.setTextColor(Color.WHITE) //  color del texto
                searchEditText.setHintTextColor(Color.DKGRAY) // Caolor del hint

                return true
            }
        })
    }

    private fun filterShoeList(query: String?) {
        val filteredList = originalShoeList.filter { shoe ->
            shoe.name.contains(query ?: "", ignoreCase = true)
        }
        shoeAdapter.updateItems(filteredList) // update a la lista filtrada
    }
}
