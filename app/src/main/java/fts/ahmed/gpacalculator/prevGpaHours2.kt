package fts.ahmed.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import fts.ahmed.gpacalculator.databinding.ActivityPrevGpaHoursBinding

class prevGpaHours2 : AppCompatActivity() {
    private lateinit var binding: ActivityPrevGpaHoursBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBindingSetUp()
    }

    private fun viewBindingSetUp() {
        binding = ActivityPrevGpaHoursBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    public fun btnOnClick(view : View) {
        val gpa = binding.etGpa.text.toString()
        val hours = binding.etHours.text.toString()
        if (gpa.isEmpty() || hours.isEmpty())
            Toast.makeText(this, "please enter all values", Toast.LENGTH_SHORT).show()
        else
            goToCoursesResultsIntent(gpa, hours)
    }

    private fun goToCoursesResultsIntent(gpa: String, hours: String) {
        val intent = Intent(this, CoursesResults3::class.java)
        intent.putExtra("GPA", gpa)
        intent.putExtra("HOURS", hours)
        startActivity(intent)
    }
}
