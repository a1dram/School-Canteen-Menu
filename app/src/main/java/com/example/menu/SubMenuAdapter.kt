package com.example.menu

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class SubMenuAdapter(
    var items: List<SubMenuItem>, var context: Context,
    val menuTitle: String, val menuType: String, val itemsList: RecyclerView,
    val menu: Map<String, Map<Int, List<String>>>, val lastPosition: String
) : RecyclerView.Adapter<SubMenuAdapter.MyViewHolder>() {

    private var lastSelected = lastPosition.toInt()
    private var defaultHeight = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_menu, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title

        val param = holder.cardView.layoutParams

        val imageId = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )

        if (defaultHeight == 0) {
            defaultHeight = param.height
        }

        param.height = defaultHeight
        if (position == lastSelected) {
            param.height += defaultHeight / 8
        }

        holder.icon.setImageResource(imageId)

        holder.cardView.setOnClickListener {
            val listItems = arrayListOf<Item>()

            if (position != lastSelected) {
                notifyItemChanged(lastSelected)
                lastSelected = position.toInt()
                notifyItemChanged(position)
            }

            for ((dish_type, dish_info) in menu) {
                if (dish_type == menuType)
                    for ((index, about) in dish_info) {
                        if (about[3] == items[position].image)
                            listItems.add(
                                Item(
                                    index, "item${menuType}${index}", about[0], about[1],
                                    about[2].toInt()
                                )
                            )
                    }
            }

            itemsList.layoutManager = GridLayoutManager(context, 2)
            itemsList.adapter = ItemsAdapter(
                listItems, context, menuTitle, menuType, items[position].image, position.toString()
            )

        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon)
        val title: TextView = view.findViewById(R.id.icon_name)
        val cardView: CardView = view.findViewById(R.id.cardViewIcon)


    }

}
