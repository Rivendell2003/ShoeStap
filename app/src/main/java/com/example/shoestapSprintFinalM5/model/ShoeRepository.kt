package com.example.shoestapSprintFinalM5.model

import com.example.shoestapSprintFinalM5.R
import android.util.Log

object ShoeRepository {
    // Lista de zapatos disponibles
    private val shoeList = listOf(
        Item("Zapato Clásico", R.drawable.hp1100111117_111_1, 24990, "Zapato elegante para ocasiones formales."),
        Item("Zapato Urbano", R.drawable.hp29701145_670_1, 35990, "Ideal para el día a día, con estilo moderno."),
        Item("Zapato Deportivo", R.drawable.hp110011362_111_1, 47990, "Comodidad y estilo en cada paso."),
        Item("Zapato Casual", R.drawable.hp11001111255_lb7_1, 62990, "Perfecto para actividades deportivas y casuales."),
        Item("Zapato Vintage", R.drawable._03490_1200_1600, 45990, "Diseño clásico que nunca pasa de moda."),
        Item("Zapato de Verano", R.drawable._61761_1200_1600, 31990, "Ligero y fresco, ideal para el verano."),
        Item("Zapato Premium", R.drawable._62410_2400_3200, 70900, "Para los que buscan calidad y durabilidad."),
        Item("Zapato Elegante", R.drawable._68201_2400_3200, 58990, "Combinación perfecta entre comodidad y estilo."),
        Item("Zapato Paseo", R.drawable._69359_2400_3200, 24990, "Ideal para salir a pasear o hacer recados."),
        Item("Zapato Relax", R.drawable._72385_1200_1600, 39990, "Zapato casual para un look relajado."),
        Item("Zapato Clásico Moderno", R.drawable._73495_2400_3200, 57990, "Un clásico que debes tener en tu armario."),
        Item("Zapato de Nieve", R.drawable._78541_2400_3200, 79990, "Ideal para climas fríos y actividades invernales."),
        Item("Zapato de Fiesta", R.drawable._84777_2400_3200, 64990, "Perfecto para eventos y celebraciones especiales."),
        Item("Zapato de Oficina", R.drawable._87122_1200_1600, 58990, "Elegante y cómodo para un día de trabajo."),
        Item("Zapato de Senderismo", R.drawable._90095_2400_3200, 69990, "Diseñado para aventuras al aire libre."),
        Item("Zapato de Baile", R.drawable._95842_1200_1600, 55990, "Para disfrutar de la pista de baile con estilo."),
        Item("Zapato de Gym", R.drawable.hp10201162648_c87_1, 47990, "Comodidad y soporte para tus entrenamientos."),
        Item("Zapato de Playa", R.drawable.hp10201162658_aba_1, 28990, "Ideal para disfrutar de días soleados en la playa."),
        Item("Zapato para Niños", R.drawable.hp11001111123_111_1, 24990, "Comodidad y durabilidad para los más pequeños."),
        Item("Zapato de Invierno", R.drawable.hp1100111117_111_1, 69990, "Mantén tus pies calientes y a la moda."),
        Item("Zapato Minimalista", R.drawable.hp11001111181_dk1_1, 39990, "Diseño simple y elegante para cualquier ocasión."),
        Item("Zapato de Escalada", R.drawable.hp11001111255_lb7_1, 57990, "Diseñado para ofrecer tracción y soporte en roca."),
        Item("Zapato de Camping", R.drawable.hp110011362_111_1, 54990, "Ideal para aventuras al aire libre y acampadas."),
        Item("Zapato de Moda", R.drawable.hp125011124_dk1_1, 49990, "Tendencias actuales con un toque único."),
        Item("Zapato Deportivo Casual", R.drawable.hp19601171_386_1, 45990, "Estilo deportivo para el uso diario."),
        Item("Zapato de Trail", R.drawable.hp19601171_dk1_1, 64990, "Perfecto para correr en terrenos irregulares."),
        Item("Zapato Ecológico", R.drawable.hp29701145_670_1, 31990, "Hecho de materiales sostenibles y amigables con el medio ambiente.")
    )

    // el carrito de compras
    private val cartItems = mutableListOf<Item>()

    // es para obtener la lista de zapatos
    fun returnShoeList(): List<Item> = shoeList

    // forma para obtener los ítems del carrito
    fun returnCartItems(): List<Item> = cartItems

    // metodo para agregar un zapato al carrito
    fun addToCart(item: Item) {
        if (!cartItems.any { it.name == item.name }) {
            cartItems.add(item)
            Log.d("ShoeRepository", "${item.name} agregado al carrito")
        } else {
            Log.d("ShoeRepository", "${item.name} ya está en el carrito")
        }
    }

    // Método para eliminar un zapato del carrito
    fun removeShoe(item: Item) {
        if (cartItems.remove(item)) {
            Log.d("ShoeRepository", "${item.name} eliminado del carrito")
        } else {
            Log.d("ShoeRepository", "${item.name} no se encontró en el carrito")
        }
    }

    // Método para calcular el total del carrito
    fun getTotalPrice(): Int = cartItems.sumOf { it.price }
}
