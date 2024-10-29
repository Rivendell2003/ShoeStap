package com.example.shoestapSprintFinalM5.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestapSprintFinalM5.R
import com.example.shoestapSprintFinalM5.adapter.CartAdapter
import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository

class CartActivity : AppCompatActivity(), CartView {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView
    private lateinit var emptyCartMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setupViews()
        setupRecyclerView()
        showCartItems(ShoeRepository.returnCartItems()) // pone los ítems en el carrito
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.cart_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cartAdapter = CartAdapter(listOf()) { item ->
            ShoeRepository.removeShoe(item)
            showCartItems(ShoeRepository.returnCartItems()) // update la vista después de eliminar
        }
        recyclerView.adapter = cartAdapter
    }

    private fun setupViews() {
        totalPriceTextView = findViewById(R.id.total_price_text_view)
        emptyCartMessage = findViewById(R.id.empty_cart_message)

        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish() // se va alaa actividad anterior
        }

        // la config para el botón "Pagar"
        val payButton: Button = findViewById(R.id.pay_button)
        payButton.setOnClickListener {
            Toast.makeText(this, "Se implementará en el futuro...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showCartItems(items: List<Item>) {
        Log.d("CartActivity", "Items en el carrito: ${items.size}")
        cartAdapter.updateItems(items)
        updateTotalPrice(items)

        if (items.isEmpty()) {
            showEmptyCartMessage()
        } else {
            hideEmptyCartMessage()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("CartActivity", "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CartActivity", "onDestroy called")
    }

    private fun updateTotalPrice(items: List<Item>) {
        val totalPrice = ShoeRepository.getTotalPrice().toDouble()
        totalPriceTextView.text = getString(R.string.total_price, totalPrice)
    }

    override fun showTotalPrice(total: Double) {
        totalPriceTextView.text = getString(R.string.total_price, total)
    }

    override fun showEmptyCartMessage() {
        emptyCartMessage.visibility = View.VISIBLE
    }

    private fun hideEmptyCartMessage() {
        emptyCartMessage.visibility = View.GONE
    }
}
