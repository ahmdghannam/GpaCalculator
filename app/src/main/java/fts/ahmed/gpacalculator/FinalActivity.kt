package fts.ahmed.gpacalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fts.ahmed.gpacalculator.databinding.ActivityFinalBinding
import fts.ahmed.gpacalculator.databinding.ActivityMainBinding

class FinalActivity : AppCompatActivity() {
    lateinit var binding:ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val semesterGpa=intent.getStringExtra("semesterGpa")
        val cumulativeGpa=intent.getStringExtra("cumulativeGpa")
        val isItIncreased =intent.getBooleanExtra("isItIncreased",false)

        binding.valueSemeterGpa.text=semesterGpa
        binding.valueCumulativeGpa.text=cumulativeGpa

        if (!isItIncreased) binding.increasedDecreased.text="this is Your GPA"

    }
}