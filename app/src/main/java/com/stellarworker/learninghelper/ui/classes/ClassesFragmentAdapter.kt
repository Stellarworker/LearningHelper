package com.stellarworker.learninghelper.ui.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.stellarworker.learninghelper.R
import com.stellarworker.learninghelper.domain.ClassesEx
import com.stellarworker.learninghelper.utils.EMPTY
import com.stellarworker.learninghelper.utils.show

class ClassesFragmentAdapter(
    private val openSkype: (() -> Unit)? = null
) :
    RecyclerView.Adapter<ClassesFragmentAdapter.RecyclerItemViewHolder>() {
    private var classes: List<ClassesEx> = listOf()

    fun setData(classes: List<ClassesEx>) {
        this.classes = classes
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: AppCompatTextView =
            itemView.findViewById(R.id.classes_list_item_title)
        private val time: AppCompatTextView =
            itemView.findViewById(R.id.classes_list_item_time)
        private val teacher: AppCompatTextView =
            itemView.findViewById(R.id.classes_list_item_teacher)
        private val description: AppCompatTextView =
            itemView.findViewById(R.id.classes_list_item_description)
        private val skype: CardView =
            itemView.findViewById(R.id.classes_list_item_skype)

        fun bind(classesEx: ClassesEx) {
            title.text = classesEx.title
            time.text = "${classesEx.timeBegin} - ${classesEx.timeEnd}"
            teacher.text =
                itemView.context.getString(R.string.classes_list_item_teacher, classesEx.teacher)
            if (classesEx.description != String.EMPTY) {
                description.text = classesEx.description
                description.show()
            }
            if (classesEx.openInSkype) {
                skype.show()
                itemView.setOnClickListener {
                    openSkype?.invoke()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.classes_list_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(classes[position])
    }

    override fun getItemCount() = classes.size
}