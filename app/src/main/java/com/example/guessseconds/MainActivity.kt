package com.example.guessseconds

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        tvResult.visibility = View.GONE

        btGuessSecond.setOnClickListener {
            ivOclock.visibility = View.VISIBLE
            tvResult.visibility = View.GONE
            val random: Int = (5..25).random()
            Log.e("MyLog", random.toString())
            Glide.with(this).load(R.drawable.icons8_oclock).into(ivOclock)
            btGuessSecond.visibility = View.GONE

            CoroutineScope(Dispatchers.Main).launch {
                val mill: Long = random * 1000L
                delay(mill)
                Log.e("MyLog", mill.toString())
                etSec.visibility = View.VISIBLE
                ivOclock.visibility = View.GONE
            }

            etSec.setOnClickListener {
                if (etSec.text.toString().toInt().equals(random)) {
                    tvResult.visibility = View.VISIBLE
                    etSec.visibility = View.GONE
                    etSec.text.clear()
                    btGuessSecond.visibility= View.VISIBLE
                    tvResult.text = "ВЫ УГАДАЛИ"
                    ivOclock.visibility = View.VISIBLE
                    Glide.with(this).load(R.drawable.icons8_victory1).into(ivOclock)
                    btGuessSecond.text = "Начать заново"
                } else {
                    tvResult.visibility = View.VISIBLE
                    etSec.visibility = View.GONE
                    etSec.text.clear()
                    btGuessSecond.visibility= View.VISIBLE
                    tvResult.text = "Вы НЕ Угадали"
                    ivOclock.visibility = View.VISIBLE
                    Glide.with(this).load(R.drawable.icons8_bad2).into(ivOclock)
                    btGuessSecond.text = "Начать заново"
                }
            }

        }
    }
}