package com.example.shoestapSprintFinalM5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestapSprintFinalM5.R
import com.example.shoestapSprintFinalM5.adapter.CartAdapter
import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository
import com.example.shoestapSprintFinalM5.presenter.CartPresenter

@Suppress("DEPRECATION")
class CartFragment(private val shoeRepository: ShoeRepository) : Fragment(), CartView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyCartMessage: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var backButton: Button
    private lateinit var cartPresenter: CartPresenter
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.cart_recycler_view)
        emptyCartMessage = view.findViewById(R.id.empty_cart_message)
        totalPriceTextView = view.findViewById(R.id.total_price_text_view)
        backButton = view.findViewById(R.id.back_button)

        setupRecyclerView()
        cartPresenter = CartPresenter(this, shoeRepository)
        cartPresenter.getCartItems()

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun showCartItems(items: List<Item>) {
        if (items.isEmpty()) {
            showEmptyCartMessage()
        } else {
            hideEmptyCartMessage()
            updateCart(items) // call a updateCart aquí
        }
    }

    private fun updateCart(items: List<Item>) {
        cartAdapter = CartAdapter(items) { item ->
            shoeRepository.removeShoe(item)
            cartPresenter.getCartItems() // un update la lista después de eliminar
        }
        recyclerView.adapter = cartAdapter
        showTotalPrice(shoeRepository.getTotalPrice().toDouble()) // update el precio total
    }

    override fun showTotalPrice(total: Double) {
        totalPriceTextView.text = getString(R.string.total_price, total)
    }

    override fun showEmptyCartMessage() {
        emptyCartMessage.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideEmptyCartMessage() {
        emptyCartMessage.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
