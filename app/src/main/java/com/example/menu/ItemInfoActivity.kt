package com.example.menu

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet.Layout
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

class ItemInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val title: TextView = findViewById(R.id.item_full_title)
        val text: TextView = findViewById(R.id.item_full_desc)
        val back: ImageView = findViewById(R.id.backButton)
        val infoImage: ImageView = findViewById(R.id.infoImage)
        val buyButton: Button = findViewById(R.id.buyButton)

        val menuTitle = intent.getStringExtra("menuTitle")
        val menuType = intent.getStringExtra("menuType")
        val imageName = intent.getStringExtra("imageName")
        val subType = intent.getStringExtra("subType")
        val lastSubSelected = intent.getStringExtra("lastSubSelected")

        val imageId = this.resources.getIdentifier(
            imageName,
            "drawable",
            this.packageName
        )

        infoImage.setImageResource(imageId)

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")

        back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("menuTitle", menuTitle)
            intent.putExtra("menuType", menuType)
            intent.putExtra("subType", subType)
            intent.putExtra("lastSubSelected", lastSubSelected)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(android.support.constraint.R.anim.abc_tooltip_enter, android.support.constraint.R.anim.abc_tooltip_exit)
            finish()
        }

        buyButton.setOnClickListener {
            buyButton.text = "Заказ доставляется"
            buyButton.setBackgroundResource(R.drawable.button_mainback_clicked)
            buyButton.isEnabled = false

            val dimmerView = View(this)
            dimmerView.setBackgroundColor(Color.parseColor("#80000000")) // Черный цвет с небольшой прозрачностью
            val layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            val parentLayout = findViewById<ViewGroup>(android.R.id.content)
            parentLayout.addView(dimmerView, layoutParams)

            val acceptedOrderLayout = layoutInflater.inflate(R.layout.accepted_order, null)


            val dialog = Dialog(this)
            dialog.setContentView(acceptedOrderLayout)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.show()

            val closeButton = acceptedOrderLayout.findViewById<Button>(R.id.acceptedHide)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setOnDismissListener {
                if (!dialog.isShowing) {
                    parentLayout.removeView(dimmerView)
                }
            }
        }

    }

}