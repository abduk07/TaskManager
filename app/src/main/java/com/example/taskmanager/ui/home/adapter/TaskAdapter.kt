package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTasksBinding
import com.example.taskmanager.model.Task

class TaskAdapter(private val longClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var arrayList = ArrayList<Task>()

    //    fun addData(task: Task) {
//        arrayList.add(0, task)
//        notifyItemChanged(0)
//    }
    fun addData(tasks: List<Task>) {
        arrayList.clear()
        arrayList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTasksBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class TaskViewHolder(private val binding: ItemTasksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            with(binding) {
                tvTitle.text = arrayList[position].title
                tvDesc.text = arrayList[position].desc

                itemView.setOnLongClickListener {
                    longClick(arrayList[adapterPosition])
                    false
                }
            }
        }
    }
}
