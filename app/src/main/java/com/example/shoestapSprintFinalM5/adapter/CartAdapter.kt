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

class CartAdapter(
    private var items: List<Item>,
    private val onRemoveClick: (Item) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.cart_shoe_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.cart_shoe_price)
        private val itemImageView: ImageView = itemView.findViewById(R.id.cart_shoe_image)
        private val removeButton: Button = itemView.findViewById(R.id.cart_remove_button)

        fun bind(item: Item) {
            nameTextView.text = item.name
            priceTextView.text = "$${item.price}" // fformato del precio
            itemImageView.setImageResource(item.imageResId)

            removeButton.setOnClickListener { onRemoveClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}
