package fts.ahmed.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import fts.ahmed.gpacalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // binding.root is the view not the context
        buttonClickListener()
    }

    private fun buttonClickListener(){
        binding.toPrevGpaHoursBtn.setOnClickListener {
            startActivity(Intent(this,prevGpaHours2::class.java))
        }
    }

}