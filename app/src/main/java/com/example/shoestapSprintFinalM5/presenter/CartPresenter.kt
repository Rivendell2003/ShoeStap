package com.example.shoestapSprintFinalM5.presenter

import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository
import com.example.shoestapSprintFinalM5.view.CartView

class CartPresenter(private val cartView: CartView, private val shoeRepository: ShoeRepository) {

    fun addItemToCart(item: Item) {
        shoeRepository.addToCart(item)
        updateCartView()
    }

    fun removeItem(item: Item) {
        shoeRepository.removeShoe(item)
        updateCartView()
    }

    fun getCartItems() {
        updateCartView()
    }

    private fun updateCartView() {
        val items = shoeRepository.returnCartItems()
        cartView.showCartItems(items)
        cartView.showTotalPrice(shoeRepository.getTotalPrice().toDouble())
    }
}
