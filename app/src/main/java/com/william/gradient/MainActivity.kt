package com.william.gradient

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author William
 * @date 2020/7/5 22:04
 * Class Comment：
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_translate) {
            gradientTextView1.translateAnimate = !gradientTextView1.translateAnimate
            gradientTextView2.translateAnimate = !gradientTextView2.translateAnimate
            gradientTextView3.translateAnimate = !gradientTextView3.translateAnimate
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val show = super.onPrepareOptionsMenu(menu)
        return if (!show) show else true
    }
}