package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTasksBinding
import com.example.taskmanager.model.Task

class TaskAdapter(private val longClick: (Task) -> Unit, private val updateClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var arrayList = ArrayList<Task>()

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
        holder.onBind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class TaskViewHolder(private val binding: ItemTasksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task) {
            with(binding) {
                tvTitle.text = task.title
                tvDesc.text = task.desc

                itemView.setOnLongClickListener {
                    longClick(arrayList[adapterPosition])
                    false
                }
                binding.root.setOnClickListener {
                    updateClick(task)
                }
            }
        }
    }
}
