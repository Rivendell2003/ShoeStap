package com.example.shoestapSprintFinalM5.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.shoestapSprintFinalM5.R
import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository

class ShoeDetailFragment : Fragment() {

    private lateinit var item: Item
    private lateinit var shoeRepository: ShoeRepository

    companion object {
        private const val ARG_SHOE_ITEM = "shoe_item"

        fun     newInstance(item: Item, shoeRepository: ShoeRepository): ShoeDetailFragment {
            return ShoeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SHOE_ITEM, item)
                }
                this.shoeRepository = shoeRepository
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // da el ítem de musando el tipo explícito
        item = arguments?.getParcelable(ARG_SHOE_ITEM, Item::class.java) ?: Item("", 0, 0, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shoe_detail, container, false)

        // la init de vistas
        val shoeImage: ImageView = view.findViewById(R.id.shoeImage)
        val shoeName: TextView = view.findViewById(R.id.shoeName)
        val shoePrice: TextView = view.findViewById(R.id.shoePrice)
        val addToCartButton: Button = view.findViewById(R.id.addToCartButton)

        // config de datos en las vistas
        shoeImage.setImageResource(item.imageResId)
        shoeName.text = item.name
        shoePrice.text = getString(R.string.shoe_price_format, item.price)

        // config del botón para agregar al carrito
        addToCartButton.setOnClickListener {
            shoeRepository.addToCart(item)
            Toast.makeText(requireContext(), "Zapato agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
