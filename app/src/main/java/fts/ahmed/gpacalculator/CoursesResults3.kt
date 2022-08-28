package fts.ahmed.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fts.ahmed.gpacalculator.databinding.ActivityCoursesResultsBinding

//recycler view activity
class CoursesResults3 : AppCompatActivity() {

    private lateinit var binding: ActivityCoursesResultsBinding
    val list = mutableListOf<Entity>()
    lateinit var gradesToPoints: HashMap<String, Double>
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSetup()
        populateList()
        adapter = Adapter(list, this)
        binding.rv.adapter = adapter

        val gpa = intent.getStringExtra("GPA")?.toDouble()
        val hours = intent.getStringExtra("HOURS")?.toDouble()
        hashMapInit()
        binding.btnCalcu.setOnClickListener {
            if (noItemSelected(adapter.list)) Toast.makeText(
                this,
                "please enter at least one mark",
                Toast.LENGTH_SHORT
            ).show()
            else addCoursesDataFromRV(3.0, 100.0)
        }

    }

    private fun noItemSelected(list: MutableList<Entity>): Boolean {
        list.forEach {
            if (it.mark.length < 3) return false
        }
        return true
    }

    private fun hashMapInit() {
        gradesToPoints = hashMapOf(
            "A" to 4.0,
            "A-" to 3.7,
            "B+" to 3.3,
            "B" to 3.0,
            "B-" to 2.7,
            "C+" to 2.3,
            "C" to 2.0,
            "C-" to 1.7,
            "D+" to 1.3,
            "D" to 1.0,
            "F" to 0.0,
            "W" to -1.0,
            "Ignore" to -1.0
        )
    }

    private fun populateList() {
        for (i in 1..7) {
            list.add(Entity(0, "A", 0, "A"))
        }
    }

    private fun bindingSetup() {
        binding = ActivityCoursesResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun letterToPoint(mark: String): Double? {
        return gradesToPoints[mark]
    }

    private fun addCoursesDataFromRV(prevGpa: Double, prevHours: Double) {
        var semesterGpa = 0.0
        var cumulativeGpa = 0.0
        var semesterPoints = 0.0
        var semeterHours = 0

        adapter.list.forEach { course ->
            if (course.mark == "Ignore" || course.mark == "W") return@forEach
            var isItRepeated: Boolean = course.prevMarkIfRe.length < 3

            semesterPoints += (course.hours * letterToPoint(course.mark)!!)

            semeterHours += course.hours
        }
        semesterGpa = semesterPoints / semeterHours
        var pointsBefore = prevGpa * prevHours
        cumulativeGpa = (pointsBefore + semesterPoints) / (prevHours + semeterHours)
//        Toast.makeText(this, semesterGpa.toString() + " --  " + cumulativeGpa, Toast.LENGTH_LONG)
//            .show()

        val intent =Intent(this,FinalActivity::class.java)
        intent.putExtra("semesterGpa",semesterGpa.toString())
        intent.putExtra("cumulativeGpa",cumulativeGpa.toString())
        intent.putExtra("isItIncreased",cumulativeGpa>prevGpa)
        startActivity(intent)

    }


}

fun Boolean.toInt() = if (this) 1 else 0