package com.example.shoestapSprintFinalM5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoestapSprintFinalM5.R
import com.example.shoestapSprintFinalM5.model.Item
import com.example.shoestapSprintFinalM5.model.ShoeRepository

class ShoeDetailFragment : Fragment() {
    private lateinit var item: Item
    private lateinit var shoeRepository: ShoeRepository

    companion object {
        fun newInstance(item: Item, shoeRepository: ShoeRepository): ShoeDetailFragment {
            val fragment = ShoeDetailFragment()
            val args = Bundle().apply {
                putParcelable("shoe_item", item)
            }
            fragment.arguments = args
            fragment.shoeRepository = shoeRepository
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shoe_detail, container, false)

        // da el ítem desde los argumentos
        item = arguments?.getParcelable("shoe_item") ?: Item("", 0, 0, "")

        // prende las vistas
        val shoeImage: ImageView = view.findViewById(R.id.shoeImage)
        val shoeName: TextView = view.findViewById(R.id.shoeName)
        val shoePrice: TextView = view.findViewById(R.id.shoePrice)
        val addToCartButton: Button = view.findViewById(R.id.addToCartButton)

        // aqui las vistas con los datos del ítem
        shoeImage.setImageResource(item.imageResId)
        shoeName.text = item.name
        shoePrice.text = getString(R.string.shoe_price_format, item.price)

        // aca el clic del botón para agregar al carrito
        addToCartButton.setOnClickListener {
            shoeRepository.addToCart(item)
            Toast.makeText(requireContext(), "Ítem agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
