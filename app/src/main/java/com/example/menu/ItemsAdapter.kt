package com.example.menu

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.icu.text.IDNA.Info
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.io.File

class ItemsAdapter(var items: List<Item>, var context: Context,
                   val menuTitle: String, val menuType: String, val subType: String,
                   val lastSubSelected: String):
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        holder.title.text = items[pos].title
        holder.price.text = items[pos].price.toString() + "₽"

        var imageName = items[pos].image

        var imageId = context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName
        )

        if (imageId == 0) {
            imageId = context.resources.getIdentifier(
                "table",
                "drawable",
                context.packageName
            )

            imageName = "table"
        }

        holder.image.setImageResource(imageId)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ItemInfoActivity::class.java)

            intent.putExtra("menuTitle", menuTitle)
            intent.putExtra("menuType", menuType)
            intent.putExtra("subType", subType)
            intent.putExtra("imageName", imageName)
            intent.putExtra("itemTitle", items[pos].title)
            intent.putExtra("itemText", items[pos].text)
            intent.putExtra("lastSubSelected", lastSubSelected)

            context.startActivity(intent)
            (context as MenuActivity).overridePendingTransition(android.support.constraint.R.anim.abc_tooltip_enter,
                android.support.constraint.R.anim.abc_tooltip_exit)
        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val price: TextView = view.findViewById(R.id.price)
        val cardView: CardView = view.findViewById(R.id.cardView)

    }

}