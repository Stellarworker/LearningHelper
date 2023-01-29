package com.stellarworker.learninghelper.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.stellarworker.learninghelper.R
import com.stellarworker.learninghelper.domain.Homework
import com.stellarworker.learninghelper.utils.convertLongToTime
import com.stellarworker.learninghelper.utils.getCurrentTime

class HomeworkAdapter : RecyclerView.Adapter<HomeworkAdapter.RecyclerItemViewHolder>() {
    private var homeworks: List<Homework> = listOf()

    fun setData(homeworks: List<Homework>) {
        this.homeworks = homeworks
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: AppCompatTextView =
            itemView.findViewById(R.id.homework_item_title)
        private val deadline: AppCompatTextView =
            itemView.findViewById(R.id.homework_item_deadline)
        private val description: AppCompatTextView =
            itemView.findViewById(R.id.homework_item_description)

        fun bind(homework: Homework) {
            title.text = homework.title
            val days =
                convertLongToTime(homework.deadline - getCurrentTime(), "dd")
            val daysCorrected = if (days.first() == '0') days.drop(1) else days
            deadline.text =
                itemView.context.getString(R.string.homework_item_deadline, daysCorrected)
            description.text = homework.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.homework_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(homeworks[position])
    }

    override fun getItemCount() = homeworks.size
}