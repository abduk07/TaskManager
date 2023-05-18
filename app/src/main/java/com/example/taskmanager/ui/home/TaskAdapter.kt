package com.example.taskmanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTasksBinding
import com.example.taskmanager.model.Task

class TaskAdapter(val arrayList: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTasksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class TaskViewHolder(val binding: ItemTasksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            with(binding) {
                tvTitle.text = arrayList[position].title
                tvDesc.text = arrayList[position].desc
            }
        }
    }
}
