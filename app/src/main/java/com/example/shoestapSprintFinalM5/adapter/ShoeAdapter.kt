package com.example.shoestapSprintFinalM5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestapSprintFinalM5.R
import com.example.shoestapSprintFinalM5.model.Item

class ShoeAdapter(
    internal var items: List<Item>,
    private val onRemoveClick: (Item) -> Unit,
    private val onAddToCartClick: (Item) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    inner class ShoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.shoe_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.shoe_price)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.shoe_description) // este TextView describe el zapato
        private val shoeImageView: ImageView = itemView.findViewById(R.id.shoe_image)
        private val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)

        fun bind(item: Item) {
            nameTextView.text = item.name
            priceTextView.text = "$${item.price}"
            descriptionTextView.text = item.description // mete la descripción
            shoeImageView.setImageResource(item.imageResId)

            addToCartButton.setOnClickListener {
                onAddToCartClick(item) // call a la función de agregar al carrito
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shoe, parent, false)
        return ShoeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

}
