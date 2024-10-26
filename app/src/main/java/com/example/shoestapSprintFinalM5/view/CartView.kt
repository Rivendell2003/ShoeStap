package com.example.shoestapSprintFinalM5.view

import com.example.shoestapSprintFinalM5.model.Item

interface CartView {
    /**
     * en esto lista los ítems en el carrito.
     * @param items listaa de ítems que se mostrarán en el carrito.
     */
    fun showCartItems(items: List<Item>)


    /**
     * aqui  el precio total de los ítems en el carrito.
     * @param total Precio total calculado.
     */
    fun showTotalPrice(total: Double)

    /**
     * aca mustra un mensaje cuando el carrito está vacío.
     */
    fun showEmptyCartMessage()
}
