package com.william.gradient

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.william.gradient.databinding.ActivityMainBinding

/**
 * @author William
 * @date 2020/7/5 22:04
 * Class Comment：
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_translate) {
            binding.apply {
                gradientTextView1.apply {
                    translateAnimate = !translateAnimate
                    invalidate()
                }
                gradientTextView2.apply {
                    translateAnimate = !translateAnimate
                    invalidate()
                }
                gradientTextView3.apply {
                    translateAnimate = !translateAnimate
                    invalidate()
                }

                gradientTextView5.apply {
                    translateAnimate = !translateAnimate
                    invalidate()
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val show = super.onPrepareOptionsMenu(menu)
        return if (!show) show else true
    }
}