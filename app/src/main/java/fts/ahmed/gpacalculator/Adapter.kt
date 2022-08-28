package fts.ahmed.gpacalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import fts.ahmed.gpacalculator.databinding.CourseBinding

class Adapter(val list: MutableList<Entity>, val context: Context) :
    RecyclerView.Adapter<Adapter.Holder>() {

    inner class Holder(val binding: CourseBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.spMark.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    list[position].mark = binding.spMark.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            binding.spPrevMarks.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        list[position].prevMarkIfRe = binding.spPrevMarks.selectedItem.toString()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
            binding.spHours.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    list[position].hours = binding.spHours.selectedItem.toString().toInt()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val temp = CourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(temp)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.binding.tvCourse.text = "course " + (position + 1).toString()

        val marks =
            arrayOf("Ignore", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F", "W")
        val marksSpinner = ArrayAdapter(context, R.layout.spinner_item, marks)
        marksSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        holder.binding.spMark.adapter = marksSpinner

        val prevMarksSpinner = ArrayAdapter(context, R.layout.spinner_item, marks)
        prevMarksSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        holder.binding.spPrevMarks.adapter = prevMarksSpinner

        val hours = arrayOf("1", "2", "3", "4", "5", "6")
        val hoursAdapter = ArrayAdapter(context, R.layout.spinner_item, hours)
        hoursAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        holder.binding.spHours.adapter = hoursAdapter


    }

    override fun getItemCount(): Int {
        return list.size
    }


}