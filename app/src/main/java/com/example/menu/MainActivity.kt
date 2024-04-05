package com.example.menu

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable.Orientation
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMenuClickListener(R.id.hotbutton, "Горячее", "hot", "meat")
        setMenuClickListener(R.id.buffetbutton, "Буфет", "buffet", "bakery")
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    private fun setMenuClickListener(viewId: Int, menuTitle: String, menuType: String, subType: String) {
        findViewById<Button>(viewId).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java).apply {
                putExtra("menuTitle", menuTitle)
                putExtra("menuType", menuType)
                putExtra("subType", subType)
                putExtra("lastSubSelected", "0")
            }
            startActivity(intent)
            overridePendingTransition(android.support.constraint.R.anim.abc_tooltip_enter,
                android.support.constraint.R.anim.abc_tooltip_exit)
        }
    }
}
